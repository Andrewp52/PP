package com.pae.restbackend.repositories;

import com.pae.restbackend.entities.userdata.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends BaseRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
