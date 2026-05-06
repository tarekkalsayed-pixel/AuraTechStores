package org.example.repository;

import org.example.model.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<OperationHistory, Long> {
}
