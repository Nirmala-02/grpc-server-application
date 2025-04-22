package org.example.repository;

import org.example.model.TransactionData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepo extends JpaRepository<TransactionData,Integer> {
}
