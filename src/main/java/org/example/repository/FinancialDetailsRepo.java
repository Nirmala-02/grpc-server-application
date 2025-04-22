package org.example.repository;

import org.example.model.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialDetailsRepo extends JpaRepository<FinancialData,Integer> {
}
