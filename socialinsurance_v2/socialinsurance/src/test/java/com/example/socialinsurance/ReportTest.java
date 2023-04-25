package com.example.socialinsurance;

import com.example.socialinsurance.controller.ReportController;
import com.example.socialinsurance.controller.TrackingController;
import com.example.socialinsurance.dto.impl.ReportProfitDTO;
import com.example.socialinsurance.dto.impl.ReportRequestDTO;
import com.example.socialinsurance.dto.impl.ReportUserDTO;
import com.example.socialinsurance.dto.impl.UserInfoDTO;
import com.example.socialinsurance.exception.InputException;
import com.example.socialinsurance.repository.AddressRepository;
import com.example.socialinsurance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class ReportTest {


    @Autowired
    ReportController reportController;


    @Autowired
    private DataSource dataSource;

    @Test
    void testGetListReportProfitByCityAndYearSuccess(){
        List<String> cities = Arrays.asList("Ha Noi", "Ho Chi Minh");
        var reportRequest = ReportRequestDTO.builder().cities(cities)
                .startYear(2012).endYear(2023).build();
        ResponseEntity<List<ReportProfitDTO>> reportProfitList = reportController.getReportProfit(reportRequest);
        assertThat(reportProfitList.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(reportProfitList.getBody()).isNotNull();

    }
    @Test
    void testGetListReportUserByCityAndYearSuccess(){
        List<String> cities = Arrays.asList("Ha Noi", "Ho Chi Minh");
        var reportRequest = ReportRequestDTO.builder().cities(cities)
                .startYear(2012).endYear(2023).build();
        ResponseEntity<List<ReportUserDTO>> reportProfitList = reportController.getReportUser(reportRequest);
        assertThat(reportProfitList.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(reportProfitList.getBody()).isNotNull();

    }

    @Test
    void testGetListReportProfitByCityAndYearFailedWhenStartYearGreaterThanEndYear(){
        List<String> cities = Arrays.asList("Ha Noi", "Ho Chi Minh");
        var reportRequest = ReportRequestDTO.builder().cities(cities)
                .startYear(2023).endYear(2012).build();
        assertThatThrownBy(() -> reportController.getReportProfit(reportRequest)).isInstanceOf(InputException.class);

    }
    @Test
    void testGetListReportProfitByCityAndYearFailedWhenYearBeNegative(){
        List<String> cities = Arrays.asList("Ha Noi", "Ho Chi Minh");
        var reportRequest = ReportRequestDTO.builder().cities(cities)
                .startYear(-2023).endYear(2012).build();
        assertThatThrownBy(() -> reportController.getReportProfit(reportRequest)).isInstanceOf(InputException.class);

    }

}
