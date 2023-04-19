package com.example.socialinsurance.repository;

import com.example.socialinsurance.entity.Address;
import com.example.socialinsurance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    User findBySinCode(String sinCode);

//    @Query(value = "SELECT * FROM user", nativeQuery = true)
//    List<User> findUserByCityAndDistrictAndWard();
    @Query(value = "SELECT * FROM user JOIN address on user.address_id = address.id " +
            "WHERE address.city = ?1 AND address.district = ?2 AND address.ward = ?3", nativeQuery = true)
    List<User> findUserByCityAndDistrictAndWard(String city, String district, String ward);

    @Query(value = "SELECT * FROM user JOIN address on user.address_id = address.id " +
            "WHERE address.city = ?1 AND address.district = ?2", nativeQuery = true)
    List<User> findUserByCityAndDistrict(String city, String district);
    @Query(value = "SELECT * FROM user JOIN address on user.address_id = address.id " +
            "WHERE address.city = ?1", nativeQuery = true)
    List<User> findUserByCity(String city);
}

