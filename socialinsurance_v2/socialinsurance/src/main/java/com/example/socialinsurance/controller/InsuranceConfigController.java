package com.example.socialinsurance.controller;

import com.example.socialinsurance.dto.impl.InsuranceConfigDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class InsuranceConfigController {
    @PostMapping("/update")
    public ResponseEntity<String> updateConfig(@RequestBody InsuranceConfigDTO insuranceConfigDTO){
        return ResponseEntity.ok("sucess");

    }
}
