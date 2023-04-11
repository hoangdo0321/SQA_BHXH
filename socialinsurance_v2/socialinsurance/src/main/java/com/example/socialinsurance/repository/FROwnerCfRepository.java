package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.ForeignOwnerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FROwnerCfRepository extends JpaRepository<ForeignOwnerConfig, Long> {
}
