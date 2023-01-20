package com.pae.restbackend.repositories;

import com.pae.restbackend.entities.userdata.Phone;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhonesRepository extends BaseRepository<Phone, Long> {
    Optional<Phone> findByPhone(String phone);
}
