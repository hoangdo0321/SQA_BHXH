package com.example.socialinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long userId;
    private String sinCode;
    private String idCard;
    private String name;
    private String tel;
    private LocalDate dob;
    private String email;
    private String nationality;
    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "adrId")
    private Address address;
    private String detailAddress;
    @Enumerated(EnumType.STRING)
    private InsuranceType insuranceType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Job> job;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<InsuranceDetails> insuranceDetails;



}
