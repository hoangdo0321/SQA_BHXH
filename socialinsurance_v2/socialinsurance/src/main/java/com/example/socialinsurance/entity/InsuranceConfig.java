package com.example.socialinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class InsuranceConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "retire", precision = 3, scale = 1)
    private Double huuTriTuTuat;
    @Column(name = "pregant",precision = 3, scale = 1)
    private Double omDauThaiSan;
    @Column(name = "accident", precision = 3, scale = 1)
    private Double TNLD_BNN;
    @Column(name = "jobless", precision = 3, scale = 1)
    private Double BHTN;
    @Column(name = "medical", precision = 3, scale = 1)
    private Double BHYT;
    private LocalDate updateDate;

    public InsuranceConfig(Double huuTriTuTuat, Double omDauThaiSan, Double tnld_bnn, Double bhtn, Double bhyt, LocalDate updateDate) {
    }
}
