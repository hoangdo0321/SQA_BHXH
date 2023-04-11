package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.VietNamWorkerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VnWorkerCfRepository extends JpaRepository<VietNamWorkerConfig, Long> {
}
