package com.example.socialinsurance.dto.impl;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportUserDTO {
    private Integer year;
    private String city;
    private Integer numberUser;
}
