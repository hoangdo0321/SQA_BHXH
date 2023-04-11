package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.InsuranceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<InsuranceDetails, Long> {
    @Override
    List<InsuranceDetails> findAll();
}
