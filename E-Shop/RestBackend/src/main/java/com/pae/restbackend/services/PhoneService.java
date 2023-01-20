package com.pae.restbackend.services;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.dto.PhoneDto;
import com.pae.restbackend.entities.entityfactories.EntityFactory;
import com.pae.restbackend.entities.userdata.Phone;
import com.pae.restbackend.repositories.BaseRepository;
import com.pae.restbackend.repositories.PhonesRepository;
import org.springframework.stereotype.Service;


@Service
public class PhoneService extends AbstractService<Phone, Long> {
    public PhoneService(BaseRepository<Phone, Long> repository, EntityFactory<Phone> factory) {
        super(repository, factory);
    }

    @Override
    public Phone update(AbstractDto dto) {
        return null;
    }

    public Phone findOrCreate(PhoneDto dto){
        return ((PhonesRepository)repository).findByPhone(dto.getPhone())
                .orElseGet(() -> {
                    dto.setId(null);
                    return this.addNew(dto);
                });
    }
}
