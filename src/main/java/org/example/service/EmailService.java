package org.example.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final HistoryService historyService;

    EmailService(JavaMailSender mailSender, HistoryService historyService) {
        this.mailSender = mailSender;
        this.historyService = historyService;
    }

    public void sendOffer(String email, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Aura Tech Store product discount");
        message.setText("Hello, we have a special offer for you please visit our website.");
        mailSender.send(message);
        historyService.add("admin", "EMAIL", "Offer email sent to " + email + " about " + productName);
    }
}
