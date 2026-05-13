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
        Thread emailThread = new Thread(() -> sendOfferInBackground(email, productName));
        emailThread.setName("email-thread-" + emailThread.getId());
        emailThread.start();
        historyService.add("admin", "EMAIL_STARTED", "Offer email thread started for " + email + " about " + productName);
    }

    private void sendOfferInBackground(String email, String productName) {
        String threadName = Thread.currentThread().getName();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Aura Tech Store product discount");
        message.setText("we have a new offers for a limited time check our website.");

        try {
            mailSender.send(message);
            historyService.add("admin", "EMAIL", "Offer email sent to " + email + " about " + productName + " using " + threadName);
        } catch (Exception ex) {
            historyService.add("admin", "EMAIL_FAILED", "Could not send offer email to " + email + " using " + threadName);
        }
    }
}
