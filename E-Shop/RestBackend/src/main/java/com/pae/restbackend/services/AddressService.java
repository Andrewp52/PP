package com.pae.restbackend.services;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.dto.AddressDto;
import com.pae.restbackend.entities.entityfactories.EntityFactory;
import com.pae.restbackend.entities.userdata.Address;
import com.pae.restbackend.repositories.AddressRepository;
import com.pae.restbackend.repositories.BaseRepository;
import org.springframework.stereotype.Service;


@Service
public class AddressService extends AbstractService<Address, Long> {
    public AddressService(BaseRepository<Address, Long> repository, EntityFactory<Address> entityFactory) {
        super(repository, entityFactory);
    }

    public Address findOrCreate(AddressDto dto){
        return ((AddressRepository)repository).findByRegionAndAreaAndCityAndStreetAndBuildingAndAptOffice(
                        dto.getRegion(),
                        dto.getArea(),
                        dto.getCity(),
                        dto.getStreet(),
                        dto.getBuilding(),
                        dto.getAptOffice()
                )
                .orElseGet(() -> {
                    dto.setId(null);
                    return this.addNew(dto);
                });
    }
    @Override
    public Address update(AbstractDto dto) {
        return null;
    }
}
