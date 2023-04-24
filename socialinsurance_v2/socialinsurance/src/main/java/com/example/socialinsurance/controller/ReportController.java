package com.example.socialinsurance.controller;

import com.example.socialinsurance.dto.impl.ReportProfitDTO;
import com.example.socialinsurance.dto.impl.ReportRequestDTO;
import com.example.socialinsurance.dto.impl.ReportUserDTO;
import com.example.socialinsurance.service.ReportService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/static")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_MANAGER')")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/profit")
    public ResponseEntity<List<ReportProfitDTO>> getReportProfit(@RequestBody ReportRequestDTO reportRequestDTO){
        return ResponseEntity.ok(reportService.reportProfitByYearAndCities(reportRequestDTO));
    }
    @GetMapping("/user")
    public ResponseEntity<List<ReportUserDTO>> getReportUser(@RequestBody ReportRequestDTO reportRequestDTO){
        return ResponseEntity.ok(reportService.reportUserByYearAndCities(reportRequestDTO));
    }
}
