package com.example.demo.controller;

import com.example.demo.service.DefaultEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final DefaultEmailService emailService;
    @Autowired
    public EmailController(DefaultEmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(value = "/simple-email/{user-email}")
    public void sendSimpleEmail(@PathVariable("user-email") String email) {
        emailService.sendSimpleEmail(email, "Welcome", "This is a welcome email for your!!");
    }

    @GetMapping(value = "/simple-order-email/{user-email}")
    public void sendEmailAttachment(@PathVariable("user-email") String email)
            throws MessagingException, FileNotFoundException {
            emailService.sendEmailWithAttachment(email, "Order Confirmation",
                    "Thanks for your recent order",
                    "classpath:purchase_order.pdf");
    }

}
