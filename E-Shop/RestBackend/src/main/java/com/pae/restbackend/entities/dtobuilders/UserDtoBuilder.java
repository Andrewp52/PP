package com.pae.restbackend.entities.dtobuilders;

import com.pae.restbackend.dto.UserDto;
import com.pae.restbackend.entities.userdata.User;

public class UserDtoBuilder extends AbstractDtoBuilder<User, UserDto> {

    public UserDtoBuilder(User user) {
        super(user, new UserDto());
        super.dto.setUserName(super.entity.getUsername());
        super.dto.setFirstName(super.entity.getFirstName());
        super.dto.setLastName(super.entity.getLastName());
        super.dto.setEnabled(super.entity.isEnabled());
    }

    public UserDtoBuilder withRoles(){
        super.dto.setRoles(super.withNestedSet(super.entity.getRoles()));
        return this;
    }

    public UserDtoBuilder withPhones(){
        super.dto.setPhones(super.withNestedSet(super.entity.getPhones()));
        return this;
    }

    public UserDtoBuilder withAddresses(){
        super.dto.setAddresses(super.withNestedSet(super.entity.getAddresses()));
        return this;
    }

}
