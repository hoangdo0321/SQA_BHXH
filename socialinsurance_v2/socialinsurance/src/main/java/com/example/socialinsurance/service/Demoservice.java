package com.example.socialinsurance.service;

import com.example.socialinsurance.dto.demo.InsurancePayDTO;
import com.example.socialinsurance.dto.demo.UserRegisterDTO;
import com.example.socialinsurance.dto.impl.InsuranceDetailsDTO;
import com.example.socialinsurance.dto.impl.UserInfoDetailsDTO;
import com.example.socialinsurance.entity.*;
import com.example.socialinsurance.repository.AddressRepository;
import com.example.socialinsurance.repository.InsuranceRepository;
import com.example.socialinsurance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class Demoservice {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final InsuranceRepository insuranceRepository;
    public String createInsurance(InsurancePayDTO insurancePayDTO){
        var user = userRepository.findBySinCode(insurancePayDTO.getSinCode());
        String jobName = null;
        String workplace= null;
        Double salary = null;
        Job job = new Job();
        for(Job j: user.getJob()){
            if(job.isWorking()) {
//                jobName = job.getName();
//                workplace = job.getWorkplace();
//                salary = job.getSalary();
                job = j;
                break;
            }
        }

        var insuranceDetails = InsuranceDetails.builder()
                .startDate(insurancePayDTO.getStartDate())
                .expireDate(insurancePayDTO.getExpireDate())
                .insuranceCost(insurancePayDTO.getInsuranceCost())
                .type(insurancePayDTO.getType())
                .job(job)
                .user(user)
                .build();
        user.getInsuranceDetails().add(insuranceDetails);
//        userRepository.save(user);
        insuranceRepository.save(insuranceDetails);
        System.out.println(user.getInsuranceDetails());

        return "Add insurance success";
    }
    public String createUser(UserRegisterDTO userRegister){
        String pattern = "dd/MM/yyyy";

        System.out.println(userRegister.getDob() + "yyyyyyyyyyyyyyyyyyyyyyyyyyy");
        Address requestAddress =  Address.builder().city(userRegister.getCity()).district(userRegister.getDistrict())
                .ward(userRegister.getWard()).build();
        Address repoAddress = addressRepository.findByCityAndDistrictAndWard(userRegister.getCity(),
                userRegister.getDistrict(), userRegister.getWard());
        Job requestJob = Job.builder().name(userRegister.getCurrentJobName())
                .workplace(userRegister.getCurrentWorkplace()).salary(userRegister.getCurrentSalary()).isWorking(true).build();
        InsuranceType insuranceType = InsuranceType.MANDATORY_WORKER;
        switch (userRegister.getInsType()){
            case "MO": insuranceType = InsuranceType.MANDATORY_OWNER;
                break;
            case "O": insuranceType = InsuranceType.OPTIONAL;
                break;
            default:
                break;
        }

        if(repoAddress != null){
            var user = User.builder()
                    .sinCode(userRegister.getSinCode())
                    .idCard(userRegister.getIdCard())
                    .name(userRegister.getName())
                    .tel(userRegister.getTel())
                    .dob(userRegister.getDob())
                    .email(userRegister.getEmail())
                    .nationality(userRegister.getNationality())
                    .insuranceType(insuranceType)
                    .address(repoAddress)
                    .detailAddress(userRegister.getDetailAddress())
                    .build();
            repoAddress.getUser().add(user);
            for(User u: repoAddress.getUser()){
                System.out.println(u.getAddress().getCity() + " "
                        + u.getAddress().getDistrict() + " " + u.getAddress().getWard());
            }
            addressRepository.save(repoAddress);

        }
        else{
            var user = User.builder()
                    .sinCode(userRegister.getSinCode())
                    .idCard(userRegister.getIdCard())
                    .name(userRegister.getName())
                    .tel(userRegister.getTel())
                    .dob(userRegister.getDob())
                    .email(userRegister.getEmail())
                    .nationality(userRegister.getNationality())
                    .insuranceType(insuranceType)
                    .address(requestAddress)
                    .detailAddress(userRegister.getDetailAddress())
                    .build();
            requestJob.setUser(user);
            Set<User> users = new HashSet<User>();
            Set<Job> jobs = new HashSet<>();
            jobs.add(requestJob);
            user.setJob(jobs);
            users.add(user);
            requestAddress.setUser(users);
            addressRepository.save(requestAddress);
        }

        return "Add user successful";

//        user.setAddress(modelMapper.map(userRegister.getAddressRegister(), Address.class));
//        userRepository.save(user);


    }

    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "Delete user successful";
    }

    public String deleteAddress(Long id){
        addressRepository.deleteById(id);
        return "Delete user successful";
    }
}
