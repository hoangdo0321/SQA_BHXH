package com.example.socialinsurance.dto.impl;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceConfigDTO {
    private String type;
    private Double huuTriTuTuat;
    private Double omDauThaiSan;
    private Double TNLD_BNN;
    private Double BHTN;
    private Double BHYT;
}
