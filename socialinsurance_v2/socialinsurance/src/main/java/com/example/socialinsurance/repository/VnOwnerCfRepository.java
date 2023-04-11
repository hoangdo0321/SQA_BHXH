package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.VietNamOwnerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VnOwnerCfRepository extends JpaRepository<VietNamOwnerConfig, Long> {
}
