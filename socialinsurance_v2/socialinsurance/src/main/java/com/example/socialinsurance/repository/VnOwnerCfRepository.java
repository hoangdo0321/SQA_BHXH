package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.VietNamOwnerConfig;
import com.example.socialinsurance.entity.VietNamWorkerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VnOwnerCfRepository extends JpaRepository<VietNamOwnerConfig, Long> {
    List<VietNamOwnerConfig> findAllByOrderByUpdateDateDesc();
}
