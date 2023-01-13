package com.pae.restbackend.repositories;

import com.pae.restbackend.entities.userdata.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends BaseRepository<Role, Long> {
    Optional<Role> findByRole(String role);
}
