package com.pae.restbackend.entities.entityfactories;

import com.pae.restbackend.dto.AddressDto;
import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.entities.userdata.Address;

public class AddressEntityFactory implements EntityFactory<Address> {
    @Override
    public Address getEntity(AbstractDto dto) {
        AddressDto d = (AddressDto) dto;
        Address a = new Address();
        a.setId(d.getId());
        a.setCity(d.getCity());
        a.setStreet(d.getStreet());
        a.setBuilding(d.getBuilding());
        a.setAptOffice(d.getAptOffice());
        return a;
    }
}
