package org.example.repository;

import org.example.model.PreferenceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceDetailsRepo extends JpaRepository<PreferenceData,Integer> {
}
