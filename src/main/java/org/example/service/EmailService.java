package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final HistoryService historyService;

    EmailService(HistoryService historyService) {
        this.historyService = historyService;
    }

    public void sendOffer(String email, String productName) {
        Thread thread = new Thread(() -> {
            String msg = "Offer email sent to " + email;
            historyService.add("admin", "EMAIL", msg + " about " + productName);
        });
        thread.start();
    }
}
