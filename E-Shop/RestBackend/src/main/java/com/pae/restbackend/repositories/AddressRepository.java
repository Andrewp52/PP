package com.pae.restbackend.repositories;

import com.pae.restbackend.entities.userdata.Address;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {
    Optional<Address> findByRegionAndAreaAndCityAndStreetAndBuildingAndAptOffice(String reg, String area, String city, String street, String build, String apt);
}
