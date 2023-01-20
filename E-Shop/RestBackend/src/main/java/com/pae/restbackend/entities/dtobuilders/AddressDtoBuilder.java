package com.pae.restbackend.entities.dtobuilders;

import com.pae.restbackend.dto.AddressDto;
import com.pae.restbackend.entities.userdata.Address;

public class AddressDtoBuilder extends AbstractDtoBuilder<Address, AddressDto> {
    public AddressDtoBuilder(Address entity) {
        super(entity, new AddressDto());
        super.dto.setRegion(entity.getRegion());
        super.dto.setArea(entity.getArea());
        super.dto.setCity(super.entity.getCity());
        super.dto.setStreet(super.entity.getStreet());
        super.dto.setBuilding(super.entity.getBuilding());
        super.dto.setAptOffice(super.entity.getAptOffice());
    }

    public AddressDtoBuilder withUsers(){
        super.dto.setUsers(super.withNestedSet(super.entity.getUsers()));
        return this;
    }
}
