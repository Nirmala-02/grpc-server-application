package org.example.repository;

import org.example.model.CustomerProfileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepo extends JpaRepository<CustomerProfileData,Integer> {
}
