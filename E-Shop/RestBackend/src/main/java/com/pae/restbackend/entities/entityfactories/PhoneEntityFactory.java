package com.pae.restbackend.entities.entityfactories;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.dto.PhoneDto;
import com.pae.restbackend.entities.userdata.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneEntityFactory implements EntityFactory<Phone> {
    @Override
    public Phone getEntity(AbstractDto dto) {
        PhoneDto d = (PhoneDto) dto;
        Phone p = new Phone();
        p.setId(dto.getId());
        p.setPhone(d.getPhone());
        return p;
    }
}
