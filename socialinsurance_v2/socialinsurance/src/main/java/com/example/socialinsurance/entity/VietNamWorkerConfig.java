package com.example.socialinsurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "vnWorkerConfig")
public class VietNamWorkerConfig extends InsuranceConfig{
    public VietNamWorkerConfig( Double huuTriTuTuat, Double omDauThaiSan, Double TNLD_BNN, Double BHTN, Double BHYT, Date updateDate) {
        super( huuTriTuTuat, omDauThaiSan, TNLD_BNN, BHTN, BHYT, updateDate);
    }
}
