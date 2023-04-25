package com.example.socialinsurance;

import com.example.socialinsurance.controller.TrackingController;
import com.example.socialinsurance.dto.impl.UserInfoDTO;
import com.example.socialinsurance.dto.impl.UserInfoDetailsDTO;
import com.example.socialinsurance.entity.User;
import com.example.socialinsurance.exception.ErrorResponse;
import com.example.socialinsurance.exception.InputException;
import com.example.socialinsurance.repository.AddressRepository;
import com.example.socialinsurance.repository.UserRepository;
import com.example.socialinsurance.service.TrackingListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class TrackingListTest {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TrackingController trackingController;
    @Autowired
    TrackingListService trackingListService;

    @Autowired
    private DataSource dataSource;

    @Test
    void testGetListUserByCitySuccess(){
        String city = "Ha Noi";
        ResponseEntity<List<UserInfoDTO>> userList =  trackingController.trackingByAddress(city, "", "");
        assertThat(userList.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(userList.getBody()).isNotNull();
    }
    @Test
    void testGetListUserByCityDistrictSuccess(){
        ResponseEntity<List<UserInfoDTO>> userList =  trackingController.trackingByAddress("Ha Noi", "Ba Dinh", "");
        assertThat(userList.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(userList.getBody()).isNotNull();
    }
    @Test
    void testGetListUserByCityDistrictWardSuccess(){
        ResponseEntity<List<UserInfoDTO>> userList =  trackingController.trackingByAddress("Ha Noi", "Ba Dinh", "Lieu Giai");
        assertThat(userList.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(userList.getBody()).isNotNull();
    }

    @Test
    void testGetListUserByCityDistrictWardEmptyFailed(){
        assertThatThrownBy(() -> trackingController.trackingByAddress("", "", "")).isInstanceOf(InputException.class);
    }

    @Test
    void testGetListUserByDistrictEmptyFailed(){
        assertThatThrownBy(() -> trackingController.trackingByAddress("Ha Noi", "", "Lieu Giai")).isInstanceOf(InputException.class);
    }

    @Test
    void testGetListUserByWrongAddressFailed(){
        ResponseEntity<List<UserInfoDTO>> userList =  trackingController.trackingByAddress("ekfes", "re", "");
        assertThat(userList.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(userList.getBody()).isEqualTo(new ArrayList<>());
    }
    @Test
    void testGetListUserByAllSuccess(){
        ResponseEntity<List<UserInfoDTO>> userList =  trackingController.trackingAllUser();
        assertThat(userList.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(userList.getBody()).isNotNull();

    }
    @Test
    void testGetUserDetailsBySinCodeSuccess(){
        ResponseEntity<UserInfoDetailsDTO> userinfo =  trackingController.getUserDetails("0112719843");
        assertThat(userinfo.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(userinfo.getBody()).isNotNull();

    }




}
