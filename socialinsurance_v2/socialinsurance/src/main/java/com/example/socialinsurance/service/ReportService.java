package com.example.socialinsurance.service;

import com.example.socialinsurance.dto.impl.ReportProfitDTO;
import com.example.socialinsurance.dto.impl.ReportRequestDTO;
import com.example.socialinsurance.dto.impl.ReportUserDTO;
import com.example.socialinsurance.entity.Address;
import com.example.socialinsurance.entity.InsuranceDetails;
import com.example.socialinsurance.entity.User;
import com.example.socialinsurance.exception.InputException;
import com.example.socialinsurance.repository.AddressRepository;
import com.example.socialinsurance.repository.InsuranceRepository;
import com.example.socialinsurance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final InsuranceRepository insuranceRepository;

    public List<ReportProfitDTO> reportProfitByYearAndCities(ReportRequestDTO reportRequestDTO){
        int startYear = reportRequestDTO.getStartYear();
        int endYear = reportRequestDTO.getEndYear();

        List<String> cities = reportRequestDTO.getCities();
        List<ReportProfitDTO> reportProfitDTOS = new ArrayList<>();
        TreeMap<String, Double> addressMap = new TreeMap<>(new AddressComparator());
        TreeMap<Integer, TreeMap<String, Double>> profitMap = new TreeMap<>();
        //address condition
        if(cities.contains("All")){
           List<InsuranceDetails>insuranceDetailsList = insuranceRepository.findAll();
           TreeMap<Integer, Double> allProfitMap = new TreeMap<>();
           for(int year = startYear; year <= endYear; year++){
               Double allProfit = 0d;
               for(InsuranceDetails insuranceDetails: insuranceDetailsList){
                   if(year >= insuranceDetails.getStartDate().getYear() && year <= insuranceDetails.getExpireDate().getYear()){
                       allProfit += insuranceDetails.getInsuranceCost();
                   }
               }
               allProfitMap.put(year, allProfit);
           }
           for(var entry: allProfitMap.entrySet()){
               ReportProfitDTO reportProfitDTO = ReportProfitDTO.builder()
                       .year(entry.getKey())
                       .profit(entry.getValue())
                       .city("All")
                       .build();
               reportProfitDTOS.add(reportProfitDTO);
           }

        }else {

            for(int year = startYear; year <= endYear; year++){
                for(String city: cities){
                    Double profit = 0d;
                    List<Address> addresses =  addressRepository.findByCity(city);
                    for(Address address: addresses){
                        List<User> userList = new ArrayList<>(address.getUser());
                        for(User u: userList){
                            List<InsuranceDetails> insuranceDetails = new ArrayList<>(u.getInsuranceDetails());
                            for(InsuranceDetails insd: insuranceDetails){
                                if( year >= insd.getStartDate().getYear() && year <= insd.getExpireDate().getYear()){
                                    profit += insd.getInsuranceCost();
                                }
                            }
                        }
                    }
                    addressMap.put(city, profit);
                }
                profitMap.put(year, addressMap);
            }
            for(var entryProfit: profitMap.entrySet()){
                for(var entryAddress: entryProfit.getValue().entrySet()){
                    ReportProfitDTO reportProfitDTO = ReportProfitDTO.builder()
                            .year(entryProfit.getKey())
                            .city(entryAddress.getKey())
                            .profit(entryAddress.getValue()).
                            build();
                    reportProfitDTOS.add(reportProfitDTO);
                }

            }
        }

        return reportProfitDTOS;
    }

    public List<ReportUserDTO> reportUserByYearAndCities(ReportRequestDTO reportRequestDTO) {
        int startYear = reportRequestDTO.getStartYear();
        int endYear = reportRequestDTO.getEndYear();
        List<String> cities = reportRequestDTO.getCities();
        List<ReportUserDTO> reportUserDTOS = new ArrayList<>();
        TreeMap<String, Integer> addressMap = new TreeMap<>(new AddressComparator());
        TreeMap<Integer, TreeMap<String, Integer>> profitMap = new TreeMap<>();
        //address condition
        if(cities.contains("All")){
            List<InsuranceDetails>insuranceDetailsList = insuranceRepository.findAll();
            TreeMap<Integer, Integer> allUserMap = new TreeMap<>();
            for(int year = startYear; year <= endYear; year++){
                Set<Long> userIds = new HashSet<>();
                for(InsuranceDetails insuranceDetails: insuranceDetailsList){
                    if(year >= insuranceDetails.getStartDate().getYear() && year <= insuranceDetails.getExpireDate().getYear()){
                        userIds.add(insuranceDetails.getUser().getUserId());
                    }
                }
                allUserMap.put(year, userIds.size());

            }
            for(var entry: allUserMap.entrySet()){
                ReportUserDTO reportUserDTO = ReportUserDTO.builder()
                        .year(entry.getKey())
                        .city("All")
                        .numberUser(entry.getValue())
                        .build();
                reportUserDTOS.add(reportUserDTO);
            }

        }else {

            for(int year = startYear; year <= endYear; year++){
                for(String city: cities){
                    int numUser = 0;
                    List<Address> addresses =  addressRepository.findByCity(city);
                    for(Address address: addresses){
                        List<User> userList = new ArrayList<>(address.getUser());
                        for(User u: userList){
                            List<InsuranceDetails> insuranceDetails = new ArrayList<>(u.getInsuranceDetails());
                            for(InsuranceDetails insd: insuranceDetails){
                                if( year >= insd.getStartDate().getYear() && year <= insd.getExpireDate().getYear()){
                                    numUser += 1;
                                    break;
                                }
                            }

                        }
                    }
                    addressMap.put(city, numUser);
                }
                profitMap.put(year, addressMap);
            }
            for(var entryProfit: profitMap.entrySet()){
                for(var entryAddress: entryProfit.getValue().entrySet()){
                    ReportUserDTO reportUserDTO = ReportUserDTO.builder()
                            .year(entryProfit.getKey())
                            .city(entryAddress.getKey())
                            .numberUser(entryAddress.getValue()).
                            build();
                    reportUserDTOS.add(reportUserDTO);
                }

            }
        }

        return reportUserDTOS;
    }
}

class  AddressComparator implements Comparator<String> {
    private Collator collator = Collator.getInstance(new Locale("vi", "VN"));
    public int compare(String s1, String s2)
    {
        return collator.compare(s1, s2);
    }
}
