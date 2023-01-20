package com.pae.restbackend.entities.entityfactories;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.entities.userdata.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityFactory implements EntityFactory<Role> {
    @Override
    public Role getEntity(AbstractDto dto) {
        return null;
    }
}
