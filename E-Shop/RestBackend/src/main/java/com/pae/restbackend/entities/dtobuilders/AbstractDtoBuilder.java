package com.pae.restbackend.entities.dtobuilders;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.entities.AbstractEntity;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractDtoBuilder<E extends AbstractEntity, D extends AbstractDto> {
    protected E entity;
    protected D dto;

    public AbstractDtoBuilder(E entity, D dto) {
        this.entity = entity;
        this.dto = dto;
        setBaseDtoData();
    }

    protected Set<AbstractDto> withNestedSet(Set<? extends AbstractEntity> collection){
        if(collection == null){
            return null;
        }
        return collection.stream().map(e -> e.dtoBuilder().build()).collect(Collectors.toSet());
    }

    public D build(){
        return this.dto;
    }

    private void setBaseDtoData(){
        this.dto.setId(entity.getId());
        this.dto.setCreated(entity.getCreated());
        this.dto.setModified(entity.getModified());
    }
}
