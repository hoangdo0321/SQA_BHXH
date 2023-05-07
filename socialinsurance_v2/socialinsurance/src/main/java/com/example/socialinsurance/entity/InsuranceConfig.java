package com.example.socialinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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
    @Column(name = "retire")
    private Double huuTriTuTuat;
    @Column(name = "pregant")
    private Double omDauThaiSan;
    @Column(name = "accident")
    private Double TNLD_BNN;
    @Column(name = "jobless")
    private Double BHTN;
    @Column(name = "medical")
    private Double BHYT;
    private Date updateDate;

    public InsuranceConfig(Double huuTriTuTuat, Double omDauThaiSan, Double tnld_bnn, Double bhtn, Double bhyt, Date updateDate) {
        this.huuTriTuTuat = huuTriTuTuat;
        this.omDauThaiSan = omDauThaiSan;
        this.TNLD_BNN = tnld_bnn;
        this.BHTN = bhtn;
        this.BHYT = bhyt;
        this.updateDate = updateDate;
    }
}
