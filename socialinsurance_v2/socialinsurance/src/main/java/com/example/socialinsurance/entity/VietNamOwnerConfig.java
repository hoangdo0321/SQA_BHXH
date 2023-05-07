package com.example.socialinsurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vnOwnerConfig")
public class VietNamOwnerConfig extends InsuranceConfig{
    public VietNamOwnerConfig( Double huuTriTuTuat, Double omDauThaiSan, Double TNLD_BNN, Double BHTN, Double BHYT, Date updateDate) {
        super(huuTriTuTuat, omDauThaiSan, TNLD_BNN, BHTN, BHYT, updateDate);
    }


}
