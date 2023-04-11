package com.example.socialinsurance.dto.impl;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDTO {
    private int startYear;
    private int endYear;
    private List<String> cities;
}
