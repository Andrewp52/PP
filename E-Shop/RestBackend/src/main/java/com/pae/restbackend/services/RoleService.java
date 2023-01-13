package com.pae.restbackend.services;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.entities.userdata.Role;
import com.pae.restbackend.exceptions.RoleNotFoundException;
import com.pae.restbackend.repositories.BaseRepository;
import com.pae.restbackend.repositories.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractService<Role, Long> {
    public RoleService(BaseRepository<Role, Long> repository) {
        super(repository);
    }

    @Override
    public Role update(AbstractDto dto) {
        return null;
    }

    public Role findByRole(String role) {
        return ((RolesRepository)repository).findByRole(role).orElseThrow(
                () -> new RoleNotFoundException(String.format("Role with name: %s not found!", role))
        );
    }
}
