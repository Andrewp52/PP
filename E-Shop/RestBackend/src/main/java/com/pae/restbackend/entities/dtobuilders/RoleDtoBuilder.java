package com.pae.restbackend.entities.dtobuilders;

import com.pae.restbackend.dto.RoleDto;
import com.pae.restbackend.entities.userdata.Role;

public class RoleDtoBuilder extends BaseDtoBuilder<Role, RoleDto> {
    public RoleDtoBuilder(Role entity) {
        super(entity, new RoleDto());
        super.dto.setRole(super.entity.getRole());
        super.dto.setDescription(super.entity.getDescription());
    }

    public RoleDtoBuilder withUsers(){
        super.dto.setUsers(super.withNestedSet(super.entity.getUsers()));
        return this;
    }
}
