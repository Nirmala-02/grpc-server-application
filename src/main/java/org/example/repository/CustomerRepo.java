package org.example.repository;

import org.example.model.CustomerDetailsResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerDetailsResponse,Integer> {
}
