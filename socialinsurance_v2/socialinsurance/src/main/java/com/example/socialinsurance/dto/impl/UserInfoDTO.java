package com.example.socialinsurance.dto.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private String sinCode;
    private String idCard;
    private String name;
    private boolean status;
}
