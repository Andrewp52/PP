package com.pae.restbackend.entities.entityfactories;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.entities.AbstractEntity;

public interface EntityFactory <E extends AbstractEntity>{
    E getEntity(AbstractDto dto);
}
