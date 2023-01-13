package com.pae.restbackend.entities.dtobuilders;

import com.pae.restbackend.dto.PhoneDto;
import com.pae.restbackend.entities.userdata.Phone;

public class PhoneDtoBuilder extends AbstractDtoBuilder<Phone, PhoneDto> {
    public PhoneDtoBuilder(Phone entity) {
        super(entity, new PhoneDto());
        super.dto.setPhone(super.entity.getPhone());
    }

    public PhoneDtoBuilder withUsers(){
        super.dto.setUsers(super.withNestedSet(super.entity.getUsers()));
        return this;
    }
}
