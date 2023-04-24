package com.example.socialinsurance.entity;


import com.example.socialinsurance.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adrId")
    private Long id;
    private String city;
    private String district;
    private String ward;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<User> user;


}
