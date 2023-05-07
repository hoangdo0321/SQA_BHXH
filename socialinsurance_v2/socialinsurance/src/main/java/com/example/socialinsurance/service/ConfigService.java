package com.example.socialinsurance.service;

import com.example.socialinsurance.dto.impl.InsuranceConfigDTO;
import com.example.socialinsurance.entity.*;
import com.example.socialinsurance.exception.InputException;
import com.example.socialinsurance.repository.FROwnerCfRepository;
import com.example.socialinsurance.repository.FRWorkerCfRepository;
import com.example.socialinsurance.repository.VnOwnerCfRepository;
import com.example.socialinsurance.repository.VnWorkerCfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final VnWorkerCfRepository vnWorkerCfRepository;
    private final VnOwnerCfRepository vnOwnerCfRepository;
    private final FRWorkerCfRepository frWorkerCfRepository;
    private final FROwnerCfRepository frOwnerCfRepository;

    public InsuranceConfigDTO getConfig(String type){
        InsuranceConfigDTO insuranceConfigDTO = null;
        if(type.equalsIgnoreCase("LDVN")){
            List<VietNamWorkerConfig> vietNamWorkerConfigs = vnWorkerCfRepository.findAllByOrderByUpdateDateDesc();
            if(vietNamWorkerConfigs.isEmpty()) {
                throw new InputException("Chưa Có Cấu Hình Cho Lao Động Việt Nam");

            }
            VietNamWorkerConfig vietNamWorkerConfig = vietNamWorkerConfigs.get(0);

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
            List<VietNamOwnerConfig> vietNamOwnerConfigs = vnOwnerCfRepository.findAllByOrderByUpdateDateDesc();
            if(vietNamOwnerConfigs.isEmpty()){
                throw new InputException("Chưa Có Cấu Hình Cho Sử dụng Lao Động Việt Nam");
            }
            VietNamOwnerConfig vietNamOwnerConfig = vietNamOwnerConfigs.get(0);

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
            List<ForeignWorkerConfig> foreignWorkerConfigs = frWorkerCfRepository.findAllByOrderByUpdateDateDesc();
            if(foreignWorkerConfigs.isEmpty()) {
                throw new InputException("Chưa Có Cấu Hình Cho Lao Động Nước Ngoài");
            }
            ForeignWorkerConfig foreignWorkerConfig = foreignWorkerConfigs.get(0);

            insuranceConfigDTO = InsuranceConfigDTO.builder()
                    .type("LDNG")
                    .huuTriTuTuat(foreignWorkerConfig.getHuuTriTuTuat())
                    .omDauThaiSan(foreignWorkerConfig.getOmDauThaiSan())
                    .TNLD_BNN(foreignWorkerConfig.getTNLD_BNN())
                    .BHTN(foreignWorkerConfig.getBHTN())
                    .BHYT(foreignWorkerConfig.getBHYT())
                    .build();

        }

        if(type.equalsIgnoreCase("SDLDNG")){
            List<ForeignOwnerConfig> foreignOwnerConfigs = frOwnerCfRepository.findAllByOrderByUpdateDateDesc();
            if(foreignOwnerConfigs.isEmpty()){
                throw new InputException("Chưa Có Cấu Hình Cho Sử Dụng Lao Động Nước Ngoài");
            }
            ForeignOwnerConfig foreignOwnerConfig = foreignOwnerConfigs.get(0);

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
        if(insuranceConfigDTO.getType() == null || insuranceConfigDTO.getHuuTriTuTuat() == null ||
             insuranceConfigDTO.getOmDauThaiSan() == null || insuranceConfigDTO.getTNLD_BNN() == null ||
             insuranceConfigDTO.getBHTN() == null || insuranceConfigDTO.getBHYT() == null){
            throw new InputException("Cần Nhập Đủ Các Trường Để Cập Nhật");
        }
        switch (insuranceConfigDTO.getType()){
            case "LDVN":
                VietNamWorkerConfig config1 = new VietNamWorkerConfig(insuranceConfigDTO.getHuuTriTuTuat(),
                    insuranceConfigDTO.getOmDauThaiSan(),
                    insuranceConfigDTO.getTNLD_BNN(),
                    insuranceConfigDTO.getBHTN(),
                    insuranceConfigDTO.getBHYT(),
                    new Date());
                vnWorkerCfRepository.save(config1);

                break;
            case "SDLDVN":
                VietNamOwnerConfig config2 = new VietNamOwnerConfig(insuranceConfigDTO.getHuuTriTuTuat(),
                        insuranceConfigDTO.getOmDauThaiSan(),
                        insuranceConfigDTO.getTNLD_BNN(),
                        insuranceConfigDTO.getBHTN(),
                        insuranceConfigDTO.getBHYT(),
                        new Date());
                vnOwnerCfRepository.save(config2);

                break;
            case "LDNG":
                ForeignWorkerConfig config3 = new ForeignWorkerConfig(insuranceConfigDTO.getHuuTriTuTuat(),
                        insuranceConfigDTO.getOmDauThaiSan(),
                        insuranceConfigDTO.getTNLD_BNN(),
                        insuranceConfigDTO.getBHTN(),
                        insuranceConfigDTO.getBHYT(),
                        new Date());
                frWorkerCfRepository.save(config3);
                break;
            case "SDLDNG":
                ForeignOwnerConfig config4 = new ForeignOwnerConfig(insuranceConfigDTO.getHuuTriTuTuat(),
                        insuranceConfigDTO.getOmDauThaiSan(),
                        insuranceConfigDTO.getTNLD_BNN(),
                        insuranceConfigDTO.getBHTN(),
                        insuranceConfigDTO.getBHYT(),
                        new Date());
                frOwnerCfRepository.save(config4);

                break;
        }
        return "success";
    }
}
