package com.example.socialinsurance.controller;


import com.example.socialinsurance.dto.impl.InsuranceDetailsDTO;
import com.example.socialinsurance.dto.impl.UserInfoDTO;
import com.example.socialinsurance.dto.impl.UserInfoDetailsDTO;
import com.example.socialinsurance.entity.Address;
import com.example.socialinsurance.entity.InsuranceDetails;
import com.example.socialinsurance.service.TrackingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/trackinglist")
@RequiredArgsConstructor
public class TrackingController {
    private final TrackingListService service;

    @GetMapping(value = "/address")
    public ResponseEntity<List<UserInfoDTO>> trackingByAddress(
            @RequestParam(value = "city", required = true) String city,
            @RequestParam(value = "district", required = false) String district,
            @RequestParam(value = "ward", required = false) String ward
    ){
        return ResponseEntity.ok(service.getUsersByAddress(city,district, ward));
    }
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserInfoDTO>>trackingAllUser(){
        return ResponseEntity.ok(service.getAllUser());
    }

    @GetMapping(value = "/maBHXH/{code}")
    public ResponseEntity<UserInfoDetailsDTO> getUserDetails(@PathVariable("code") String code){
        return ResponseEntity.ok(service.getUSerDetails(code));
    }


}