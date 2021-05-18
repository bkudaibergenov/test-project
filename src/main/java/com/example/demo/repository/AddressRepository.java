package com.example.demo.repository;


import com.example.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    Optional<Address> findById(Long id);
    Optional<Address> findByStateName(String stateName);
    Optional<Address> findByCityName(String cityName);
    Optional<Address> findByStreetName(String streetName);
    Optional<Address> findByBuildingNumber(String buildingNumber);
    Optional<Address> findByFlatNumber(Integer flatNumber);

}


