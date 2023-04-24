package com.example.socialinsurance.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Tra ve Login
public class AuthenticationResponse {
    private String userEmail;
    private String token;
}
