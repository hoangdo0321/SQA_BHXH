package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.ForeignWorkerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FRWorkerCfRepository extends JpaRepository<ForeignWorkerConfig, Long> {
}
