package org.example.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final HistoryService historyService;

    EmailService(JavaMailSender mailSender, HistoryService historyService) {
        this.mailSender = mailSender;
        this.historyService = historyService;
    }

    @Async("emailExecutor")
    public void sendOffer(String email, String productName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Aura Tech Store product discount");
        message.setText("we have a new offers for a limited time  check our website .");
        String threadName = Thread.currentThread().getName();
        try {
            mailSender.send(message);
            historyService.add("admin", "EMAIL", "Offer email sent to " + email + " about " + productName + " using " + threadName);
        } catch (Exception ex) {
            historyService.add("admin", "EMAIL_FAILED", "Could not send offer email to " + email + " using " + threadName);
        }
    }
}
