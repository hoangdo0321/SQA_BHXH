package com.example.socialinsurance.dto.impl;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportProfitDTO {
    private Integer year;
    private String city;
    private Double profit;
}
