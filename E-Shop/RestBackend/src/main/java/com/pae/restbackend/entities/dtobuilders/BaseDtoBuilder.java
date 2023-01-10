package com.pae.restbackend.entities.dtobuilders;

import com.pae.restbackend.dto.BaseDto;
import com.pae.restbackend.entities.BaseEntity;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseDtoBuilder <E extends BaseEntity, D extends BaseDto> {
    protected E entity;
    protected D dto;

    public BaseDtoBuilder(E entity, D dto) {
        this.entity = entity;
        this.dto = dto;
        setBaseDtoData();
    }

    protected Set<BaseDto> withNestedSet(Set<? extends BaseEntity> collection){
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
