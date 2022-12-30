package com.inventory_management.Inventory.Management.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ReturnRequestEmail {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRequestEmail(
            String toEmail,
            String body,
            String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("gokuldas.sayonetech@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }
}
