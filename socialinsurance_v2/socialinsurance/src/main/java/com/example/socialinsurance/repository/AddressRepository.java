package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.Address;
import com.example.socialinsurance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCityAndDistrictAndWard(String city, String district, String ward);
    List<Address> findByCity(String city);
    List<Address> findByDistrict(String district);

    List<Address> findByWard(String ward);

}
