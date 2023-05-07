package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.ForeignOwnerConfig;
import com.example.socialinsurance.entity.VietNamWorkerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FROwnerCfRepository extends JpaRepository<ForeignOwnerConfig, Long> {
    List<ForeignOwnerConfig> findAllByOrderByUpdateDateDesc();
}
