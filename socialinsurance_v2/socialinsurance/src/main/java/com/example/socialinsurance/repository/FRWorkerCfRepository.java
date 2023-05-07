package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.ForeignWorkerConfig;
import com.example.socialinsurance.entity.VietNamWorkerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FRWorkerCfRepository extends JpaRepository<ForeignWorkerConfig, Long> {
    List<ForeignWorkerConfig> findAllByOrderByUpdateDateDesc();
}
