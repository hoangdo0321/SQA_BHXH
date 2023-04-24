package com.example.socialinsurance.controller;


import com.example.socialinsurance.dto.demo.InsurancePayDTO;
import com.example.socialinsurance.dto.demo.UserRegisterDTO;
import com.example.socialinsurance.dto.impl.UserInfoDTO;
import com.example.socialinsurance.dto.impl.UserInfoDetailsDTO;

import com.example.socialinsurance.repository.UserRepository;
import com.example.socialinsurance.service.Demoservice;
import com.example.socialinsurance.service.TrackingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final Demoservice demoservice;
    @PostMapping
    public ResponseEntity<String> access(@RequestBody UserRegisterDTO userRegister){

        return ResponseEntity.ok(demoservice.createUser(userRegister));
    }
    @GetMapping(value = "/deluser")
    public ResponseEntity<String> access(@RequestParam Long iduser){

        return ResponseEntity.ok(demoservice.deleteUser(iduser));
    }
    @GetMapping(value = "/deladdr")
    public ResponseEntity<String> access1(@RequestParam Long idaddress){

        return ResponseEntity.ok(demoservice.deleteUser(idaddress));
    }

    @GetMapping(value = "/test")
    public ResponseEntity<List<UserInfoDTO>> test(){
        List<UserInfoDTO>userInfoDTOS = new ArrayList<>();
        return ResponseEntity.ok(userInfoDTOS);
    }


    @PostMapping("/addIns")
    public ResponseEntity<String> payInsurance(@RequestBody InsurancePayDTO insurancePayDTO){

        return ResponseEntity.ok(demoservice.createInsurance(insurancePayDTO));
    }

//    @GetMapping
//    public ResponseEntity<String> access(){
//
//        return ResponseEntity.ok("Working");
//    }
}
