package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.VietNamWorkerConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VnWorkerCfRepository extends JpaRepository<VietNamWorkerConfig, Long> {
    List<VietNamWorkerConfig> findAllByOrderByUpdateDateDesc();
}
