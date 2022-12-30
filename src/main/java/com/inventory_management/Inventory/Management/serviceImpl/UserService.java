package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.entity.PasswordResetToken;
import com.inventory_management.Inventory.Management.entity.Role;
import com.inventory_management.Inventory.Management.entity.User;
import com.inventory_management.Inventory.Management.entity.VerificationToken;
import com.inventory_management.Inventory.Management.repository.PasswordResetTokenRepository;
import com.inventory_management.Inventory.Management.repository.RoleRepository;
import com.inventory_management.Inventory.Management.repository.UserRepository;
import com.inventory_management.Inventory.Management.repository.VerificatioTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private VerificatioTokenRepository verificatioTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerAsUser(User user) {
        Role role = roleRepository.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepository.save(user);
    }

    public User registerAsAdmin(User user) {
        Role role = roleRepository.findById("Admin").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepository.save(user);
    }

    public User registerAsSupplier(User user) {
        Role role = roleRepository.findById("Supplier").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepository.save(user);
    }

    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Role:User");
        roleRepository.save(userRole);

        Role supplierRole = new Role();
        userRole.setRoleName("supplier");
        userRole.setRoleDescription("Role:Supplier");
        roleRepository.save(userRole);

    }

    //@Bean
    // public PasswordEncoder passwordEncoder(){
    //return new BCryptPasswordEncoder();
    //}
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void saveVerificationTokenForUser(String token, User user) {

        VerificationToken verificationToken = new VerificationToken(user, token);
        verificatioTokenRepository.save(verificationToken);
    }

    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificatioTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
            verificatioTokenRepository.delete(verificationToken);
            return "expired";
        }
        return "valid";
    }


    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public void createPasswordRestTokenForUser(User user, String token) {

        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null) {
            return "invalid";
        }

        User user = passwordResetToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((passwordResetToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }
        userRepository.save(user);
        return "valid";
    }

    public Optional<User> getUserByPasswordResetToken(String token) {

        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    public void changePassword(User user, String newPassword) {
        user.setUserPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getUserPassword());
    }
}
