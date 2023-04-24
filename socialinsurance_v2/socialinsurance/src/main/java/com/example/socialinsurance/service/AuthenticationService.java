package com.example.socialinsurance.service;

import com.example.socialinsurance.dto.impl.AuthenticationRequest;
import com.example.socialinsurance.dto.impl.AuthenticationResponse;
import com.example.socialinsurance.dto.impl.RegisterRequest;
import com.example.socialinsurance.entity.*;
import com.example.socialinsurance.exception.InputException;
import com.example.socialinsurance.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new InputException("Email has already exist");
        }

        var user = Admin.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .tel(request.getTel())
                .build();
        repository.save(user);
        Map<String, Object> extraClaims = new HashMap<String, Object>();
        extraClaims.put("role", user.getRole());
        var jwtToken = jwtService.generateToken(extraClaims, user);
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("Getttttttttttttttttttttting");
        // authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //make action to throw exception
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InputException("Tài khoản hoặc mật khẩu không chính xác"));

        Map<String, Object> extraClaims = new HashMap<String, Object>();
        extraClaims.put("role", user.getRole());
        var jwtToken = jwtService.generateToken(extraClaims, user);

        return AuthenticationResponse.builder()
                .userEmail(user.getEmail())
                .token(jwtToken)
                .build();

    }
}
