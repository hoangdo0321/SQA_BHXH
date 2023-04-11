package com.example.socialinsurance.service;

import com.example.socialinsurance.dto.impl.InsuranceConfigDTO;
import com.example.socialinsurance.entity.*;
import com.example.socialinsurance.repository.FROwnerCfRepository;
import com.example.socialinsurance.repository.FRWorkerCfRepository;
import com.example.socialinsurance.repository.VnOwnerCfRepository;
import com.example.socialinsurance.repository.VnWorkerCfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private VnWorkerCfRepository vnWorkerCfRepository;
    private VnOwnerCfRepository vnOwnerCfRepository;
    private FRWorkerCfRepository frWorkerCfRepository;
    private FROwnerCfRepository frOwnerCfRepository;

    public InsuranceConfigDTO getConfig(String type){
        InsuranceConfigDTO insuranceConfigDTO = null;
        if(type.equalsIgnoreCase("LDVN")){
            List<VietNamWorkerConfig> vietNamWorkerConfigs = vnWorkerCfRepository.findAll();
            VietNamWorkerConfig vietNamWorkerConfig = vietNamWorkerConfigs.get(vietNamWorkerConfigs.size()-1);
            insuranceConfigDTO = InsuranceConfigDTO.builder()
                    .type("LDVN")
                    .huuTriTuTuat(vietNamWorkerConfig.getHuuTriTuTuat())
                    .omDauThaiSan(vietNamWorkerConfig.getOmDauThaiSan())
                    .TNLD_BNN(vietNamWorkerConfig.getTNLD_BNN())
                    .BHTN(vietNamWorkerConfig.getBHTN())
                    .BHYT(vietNamWorkerConfig.getBHYT())
                    .build();
        }
        if(type.equalsIgnoreCase("SDLDVN")){
            List<VietNamOwnerConfig> vietNamOwnerConfigs = vnOwnerCfRepository.findAll();
            VietNamOwnerConfig vietNamOwnerConfig = vietNamOwnerConfigs.get(vietNamOwnerConfigs.size()-1);
            insuranceConfigDTO = InsuranceConfigDTO.builder()
                    .type("SDLDVN")
                    .huuTriTuTuat(vietNamOwnerConfig.getHuuTriTuTuat())
                    .omDauThaiSan(vietNamOwnerConfig.getOmDauThaiSan())
                    .TNLD_BNN(vietNamOwnerConfig.getTNLD_BNN())
                    .BHTN(vietNamOwnerConfig.getBHTN())
                    .BHYT(vietNamOwnerConfig.getBHYT())
                    .build();

        }
        if(type.equalsIgnoreCase("LDNG")){
            List<ForeignWorkerConfig> foreignWorkerConfigs = frWorkerCfRepository.findAll();
            ForeignWorkerConfig foreignWorkerConfig = foreignWorkerConfigs.get(foreignWorkerConfigs.size()-1);
            InsuranceConfigDTO insuranceConfigDTO3 = InsuranceConfigDTO.builder()
                    .type("LDNG")
                    .huuTriTuTuat(foreignWorkerConfig.getHuuTriTuTuat())
                    .omDauThaiSan(foreignWorkerConfig.getOmDauThaiSan())
                    .TNLD_BNN(foreignWorkerConfig.getTNLD_BNN())
                    .BHTN(foreignWorkerConfig.getBHTN())
                    .BHYT(foreignWorkerConfig.getBHYT())
                    .build();

        }
        if(type.equalsIgnoreCase("SDLDNG")){
            List<ForeignOwnerConfig> foreignOwnerConfigs = frOwnerCfRepository.findAll();
            ForeignOwnerConfig foreignOwnerConfig = foreignOwnerConfigs.get(foreignOwnerConfigs.size()-1);


            insuranceConfigDTO = InsuranceConfigDTO.builder()
                    .type("SDLDNG")
                    .huuTriTuTuat(foreignOwnerConfig.getHuuTriTuTuat())
                    .omDauThaiSan(foreignOwnerConfig.getOmDauThaiSan())
                    .TNLD_BNN(foreignOwnerConfig.getTNLD_BNN())
                    .BHTN(foreignOwnerConfig.getBHTN())
                    .BHYT(foreignOwnerConfig.getBHYT())
                    .build();

        }
        return insuranceConfigDTO;


    }

    public String update(InsuranceConfigDTO insuranceConfigDTO){

        switch (insuranceConfigDTO.getType()){
            case "LDVN":
                VietNamWorkerConfig config1 = new VietNamWorkerConfig(insuranceConfigDTO.getHuuTriTuTuat(),
                        insuranceConfigDTO.getOmDauThaiSan(),
                        insuranceConfigDTO.getTNLD_BNN(),
                        insuranceConfigDTO.getBHTN(),
                        insuranceConfigDTO.getBHYT(),
                        LocalDate.now());
                vnWorkerCfRepository.save(config1);

                break;
            case "SDLDVN":
                VietNamOwnerConfig config2 = new VietNamOwnerConfig(insuranceConfigDTO.getHuuTriTuTuat(),
                        insuranceConfigDTO.getOmDauThaiSan(),
                        insuranceConfigDTO.getTNLD_BNN(),
                        insuranceConfigDTO.getBHTN(),
                        insuranceConfigDTO.getBHYT(),
                        LocalDate.now());
                vnOwnerCfRepository.save(config2);

                break;
            case "LDNG":
                ForeignWorkerConfig config3 = new ForeignWorkerConfig(insuranceConfigDTO.getHuuTriTuTuat(),
                        insuranceConfigDTO.getOmDauThaiSan(),
                        insuranceConfigDTO.getTNLD_BNN(),
                        insuranceConfigDTO.getBHTN(),
                        insuranceConfigDTO.getBHYT(),
                        LocalDate.now());
                frWorkerCfRepository.save(config3);
                break;
            case "SDLDNG":
                ForeignOwnerConfig config4 = new ForeignOwnerConfig(insuranceConfigDTO.getHuuTriTuTuat(),
                        insuranceConfigDTO.getOmDauThaiSan(),
                        insuranceConfigDTO.getTNLD_BNN(),
                        insuranceConfigDTO.getBHTN(),
                        insuranceConfigDTO.getBHYT(),
                        LocalDate.now());
                frOwnerCfRepository.save(config4);

                break;
        }
        return "success";
    }
}
