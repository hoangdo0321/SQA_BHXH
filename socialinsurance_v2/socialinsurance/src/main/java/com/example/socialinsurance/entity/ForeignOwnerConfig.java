package com.example.socialinsurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "frOwnerConfig")
public class ForeignOwnerConfig extends InsuranceConfig{
    public ForeignOwnerConfig( Double huuTriTuTuat, Double omDauThaiSan, Double TNLD_BNN, Double BHTN, Double BHYT, LocalDate updateDate) {
        super( huuTriTuTuat, omDauThaiSan, TNLD_BNN, BHTN, BHYT, updateDate);
    }


}
