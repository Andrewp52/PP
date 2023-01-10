package com.pae.restbackend.repositories;

import com.pae.restbackend.entities.userdata.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends BaseRepository<Role, Long> {

}
