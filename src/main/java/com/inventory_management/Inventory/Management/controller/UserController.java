package com.inventory_management.Inventory.Management.controller;


import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.User;
import com.inventory_management.Inventory.Management.event.RegistrationCompleteEvent;
import com.inventory_management.Inventory.Management.model.PasswordModel;
import com.inventory_management.Inventory.Management.repository.UserRepository;
import com.inventory_management.Inventory.Management.serviceImpl.EmailService;
import com.inventory_management.Inventory.Management.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void initRolesAndUser() {
        userService.initRolesAndUser();
    }


    @PostMapping("/registerAsUser")
    public Message registerNewUser(@RequestBody User user, final HttpServletRequest request) throws Exception {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            Message message = new Message();
            message.setMessage("Email Id exists");
            return message;
        }
        userService.registerAsUser(user);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        Message message = new Message();
        message.setMessage("A link has been send to your email...verify to complete your registration!!");
        return message;
    }

    @GetMapping("/verifyRegistration")
    public Message verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        Message message = null;
        if (result.equalsIgnoreCase("valid")) {
            message = new Message();
            message.setMessage("Registration is complete");
        } else {
            message.setMessage("Try again");
        }
        return message;
    }

    private String applicationUrl(HttpServletRequest request) {

        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    @GetMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {

        User user = userService.findUserByEmail(passwordModel.getEmail());

        String url = "";
        if (user != null) {

            String token = UUID.randomUUID().toString();
            userService.createPasswordRestTokenForUser(user, token);
            url = passwordResetTokenMail(user, applicationUrl(request), token);
        }
        return url;
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {

        String url = applicationUrl
                + "/savePassword?token="
                + token;
        SimpleMailMessage confirmEmail = new SimpleMailMessage();
        confirmEmail.setFrom("anson.sayonetech@gmail.com");
//        confirmEmail.setTo(user.getEmail());
        confirmEmail.setTo("anson.joseph05@gmail.com");
        confirmEmail.setSubject("Reset your Password");
        confirmEmail.setText("To confirm your Account,Click the link below:\n" + url);
        emailService.sendEmail(confirmEmail);
        return "check the email to reset password";
    }


    @PostMapping("/savePassword")
    public Message savePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel) {


        String result = userService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")) {
            Message message = new Message();
            message.setMessage("Invalid Token");
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if (user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            Message message = new Message();
            message.setMessage("Password reset successfully");
            return message;
        } else {
            Message message = new Message();
            message.setMessage("Invalid token");
            return message;

        }
    }

    @PostMapping("/changePassword")
    public Message changePassword(@RequestBody PasswordModel passwordModel) {

        User user = userService.findUserByEmail(passwordModel.getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())) {
            Message message = new Message();
            message.setMessage("Invalid old password");
            return message;
        }

        userService.changePassword(user, passwordModel.getNewPassword());
        Message message = new Message();
        message.setMessage("Password changed successfully");
        return message;
    }

    @PostMapping("/registerAsSupplier")
    public Message registerNewSupplier(@RequestBody User user, HttpServletRequest request) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            Message message = new Message();
            message.setMessage("Email Id exists");
            return message;
        }
        userService.registerAsSupplier(user);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        Message message = new Message();
        message.setMessage("A link has been send to your email...verify to complete your registration!!");
        return message;
    }


    @PostMapping("/registerAsAdmin")
    public Message registerNewAdmin(@RequestBody User user, HttpServletRequest request) {

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            Message message = new Message();
            message.setMessage("Email Id exists");
            return message;
        }
        userService.registerAsAdmin(user);
        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        Message message = new Message();
        message.setMessage("A link has been send to your email...verify to complete your registration!!");
        return message;
    }

}
