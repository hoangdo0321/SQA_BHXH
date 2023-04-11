package com.example.socialinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
public class InsuranceConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(precision = 3, scale = 1)
    private Double huuTriTuTuat;
    @Column(precision = 3, scale = 1)
    private Double omDauThaiSan;
    @Column(precision = 3, scale = 1)
    private Double TNLD_BNN;
    @Column(precision = 3, scale = 1)
    private Double BHTN;
    @Column(precision = 3, scale = 1)
    private Double BHYT;
}
