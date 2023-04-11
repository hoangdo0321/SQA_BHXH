package com.example.socialinsurance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fr_oConfig")
public class ForeignOwnerConfig extends InsuranceConfig{
}