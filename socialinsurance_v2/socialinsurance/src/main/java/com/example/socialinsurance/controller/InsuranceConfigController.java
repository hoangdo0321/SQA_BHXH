package com.example.socialinsurance.controller;

import com.example.socialinsurance.dto.impl.InsuranceConfigDTO;
import com.example.socialinsurance.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class InsuranceConfigController {
    private final ConfigService configService;
    @PostMapping("/update")
    public ResponseEntity<String> updateConfig(@RequestBody InsuranceConfigDTO insuranceConfigDTO){
        return ResponseEntity.ok(configService.update(insuranceConfigDTO));

    }

    @GetMapping("/{configType}")// configType: 4 loai = LDVN, SDLDVN, LDNG, SDLDNG
    public ResponseEntity<InsuranceConfigDTO> getInsuranceConfig(@PathVariable("configType") String configType){
        return ResponseEntity.ok(configService.getConfig(configType));

    }
}
