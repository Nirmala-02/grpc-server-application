package org.example.repository;

import org.example.model.InteractionData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionDetailsRepo extends JpaRepository<InteractionData,Integer> {
}
