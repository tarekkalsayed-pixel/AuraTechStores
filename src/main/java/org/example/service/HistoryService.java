package org.example.service;

import org.example.model.OperationHistory;
import org.example.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void add(String username, String action, String details) {
        OperationHistory history = new OperationHistory();
        history.setUsername(username);
        history.setAction(action);
        history.setDetails(details);
        history.setCreatedAt(LocalDateTime.now().toString());
        historyRepository.save(history);
    }

    public List<OperationHistory> getAll() {
        return historyRepository.findAll();
    }
}
