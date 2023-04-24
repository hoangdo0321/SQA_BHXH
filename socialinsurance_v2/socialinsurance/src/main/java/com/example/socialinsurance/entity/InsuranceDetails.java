package com.example.socialinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InsuranceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "insId")
    private Long id;
    private LocalDate startDate;
    private LocalDate expireDate;
    private Double insuranceCost;
    // two type: mandatory, optional
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "jobId")
    private Job job;

}
