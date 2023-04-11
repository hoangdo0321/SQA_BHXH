package com.example.socialinsurance.dto.demo;

import com.example.socialinsurance.dto.impl.InsuranceDetailsDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    private String sinCode;
    private String idCard;
    private String name;
    private String tel;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private LocalDate dob;
    private String email;
    private String city;
    private String district;
    private String ward;
    private String nationality;
    private String insType;
    private String detailAddress;
    private String currentJobName;
    private String currentWorkplace;
    private Double currentSalary;

}
