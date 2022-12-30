package com.inventory_management.Inventory.Management.event.listener;

import com.inventory_management.Inventory.Management.entity.User;
import com.inventory_management.Inventory.Management.event.RegistrationCompleteEvent;
import com.inventory_management.Inventory.Management.serviceImpl.EmailService;
import com.inventory_management.Inventory.Management.serviceImpl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {


    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

        SimpleMailMessage confirmEmail = new SimpleMailMessage();
        confirmEmail.setFrom("anson.sayonetech@gmail.com");
//        confirmEmail.setTo(user.getEmail());
        confirmEmail.setTo("anson.joseph05@gmail.com");
        confirmEmail.setSubject("VERIFY YOUR ACCOUNT");
        confirmEmail.setText("To confirm your Account,Click the link below:\n" + url);
        emailService.sendEmail(confirmEmail);


    }
}
