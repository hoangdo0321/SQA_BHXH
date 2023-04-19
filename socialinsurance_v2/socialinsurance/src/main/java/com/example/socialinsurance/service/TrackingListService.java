package com.example.socialinsurance.service;

import com.example.socialinsurance.dto.impl.InsuranceDetailsDTO;
import com.example.socialinsurance.dto.impl.UserInfoDTO;
import com.example.socialinsurance.dto.impl.UserInfoDetailsDTO;

import com.example.socialinsurance.entity.*;
import com.example.socialinsurance.repository.AddressRepository;
import com.example.socialinsurance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TrackingListService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;




    public  boolean checkStatus(User user){
        String status = "";
        LocalDate currentDate = LocalDate.now();
        List<InsuranceDetails>insuranceDetailsList = new ArrayList<>(user.getInsuranceDetails());
        if(insuranceDetailsList.isEmpty()){
            return false;
        }
        Collections.sort(insuranceDetailsList, new DateComparator());
        LocalDate startD = insuranceDetailsList.get(insuranceDetailsList.size()-1).getStartDate();
        LocalDate expireD = insuranceDetailsList.get(insuranceDetailsList.size()-1).getExpireDate();
        if( (currentDate.isEqual(startD) || currentDate.isAfter(startD))
                    && (currentDate.isEqual(expireD) || currentDate.isBefore(expireD)) ){
            return true;
        }
        return false;
    }

    public Long totalInsurancePayTime(User user){
        String status = "";
        LocalDate currentDate = LocalDate.now();
        List<InsuranceDetails>insuranceDetailsList = new ArrayList<>(user.getInsuranceDetails());
        Collections.sort(insuranceDetailsList, new DateComparator());
        return ChronoUnit.DAYS.between(insuranceDetailsList.get(0).getStartDate(),
                insuranceDetailsList.get(insuranceDetailsList.size()-1).getExpireDate()) + 1;
    }

    public List<UserInfoDTO> getAllUser(){
        List<User> users = userRepository.findAll();
        List<UserInfoDTO>userInfoDTOS = new ArrayList<>();
        for(User u: users){
            var userinfo = modelMapper.map(u, UserInfoDTO.class);
            userInfoDTOS.add(userinfo);

        }
        Collections.sort(userInfoDTOS, new UserComparator());
        return userInfoDTOS;

    }


    public List<UserInfoDTO> getUsersByAddress(String city, String district, String ward){
        System.out.println(city);
        System.out.println(district);
        System.out.println(ward);

       // List<User> users= new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        if(city != null && district != null && ward != null){
            Address fullAddress = addressRepository.findByCityAndDistrictAndWard(city, district, ward);
            addresses.add(fullAddress);
            //users.addAll(userRepository.findUserByCityAndDistrictAndWard(city, district, ward));
        } else if (city != null && district != null && ward == null) {
            addresses.addAll(addressRepository.findByCityAndDistrict(city, district));
           // users.addAll(userRepository.findUserByCityAndDistrict(city, district));
        } else if (city != null  && district == null && ward == null) {
            addresses.addAll(addressRepository.findByCity(city));
            //users.addAll(userRepository.findUserByCity(city));

        } else if (city == null  && district == null && ward == null) {
            return null;

        }


        Set<User> users = new HashSet<User>();
        for (Address addr: addresses){
            users.addAll(addr.getUser());
        }
        List<User> userList = new ArrayList<>(users);
        List<UserInfoDTO> userInfoDTOS = new ArrayList<>();
        for(User u: users){
            var userinfo = UserInfoDTO.builder()
                    .sinCode(u.getSinCode())
                    .idCard(u.getIdCard())
                    .name(u.getName())
                    .status(checkStatus(u))
                    .build();
            userInfoDTOS.add(userinfo);

        }
        Collections.sort(userInfoDTOS, new UserComparator());
//        for(User u: userList){
//            System.out.println(u.getFullName());
//        }


        return userInfoDTOS;
    }


    public UserInfoDTO getUsersBySinCODE(String code){
        return modelMapper.map(userRepository.findBySinCode(code), UserInfoDTO.class);
    }



    public UserInfoDetailsDTO getUSerDetails(String code){
        User user = userRepository.findBySinCode(code);
        List<InsuranceDetails> insuranceDetails = new ArrayList<>();
        List<InsuranceDetailsDTO> insuranceDetailsDTOS = new ArrayList<>();
        Job currentJob = new Job();
        for(InsuranceDetails insd : insuranceDetails){
            var insdDTO = InsuranceDetailsDTO.builder()
                    .startDate(insd.getStartDate())
                    .expireDate(insd.getExpireDate())
                    .insuranceCost(insd.getInsuranceCost())
                    .jobName(insd.getJob().getName())
                    .workplace(insd.getJob().getWorkplace())
                    .salary(insd.getJob().getSalary())
                    .build();
            if(insd.getJob().isWorking()){
                currentJob = insd.getJob();
            }

            insuranceDetailsDTOS.add(insdDTO);
        }


        var userdto = UserInfoDetailsDTO.builder()
                .sinCode(user.getSinCode())
                .idCard(user.getIdCard())
                .name(user.getName())
                .tel(user.getTel())
                .dob(user.getDob())
                .email(user.getEmail())
                .city(user.getAddress().getCity())
                .district(user.getAddress().getDistrict())
                .ward(user.getAddress().getWard())
                .detailAddress(user.getDetailAddress())
                .currentJobName(currentJob.getName())
                .currentSalary(currentJob.getSalary())
                .currentWorkplace(currentJob.getWorkplace())
                .insuranceDetails(insuranceDetailsDTOS)
                .nationality(user.getNationality())
                .insType(user.getInsuranceType().toString())
                .timeJoined(totalInsurancePayTime(user))
                .build();



        return userdto;

    }







}
