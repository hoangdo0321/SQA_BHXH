package com.example.socialinsurance.dto.impl;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDTO {
    private Integer startYear;
    private Integer endYear;
    private List<String> cities;
}
