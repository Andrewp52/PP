package com.pae.restbackend.services;

import com.pae.restbackend.dto.BaseDto;
import com.pae.restbackend.entities.BaseEntity;
import com.pae.restbackend.exceptions.EntityNotFoundException;
import com.pae.restbackend.exceptions.NewEntityIdIsNotNullException;
import com.pae.restbackend.repositories.BaseRepository;


public abstract class BaseService <E extends BaseEntity, I> {
    protected final BaseRepository<E, I> repository;

    public BaseService(BaseRepository<E, I> repository) {
        this.repository = repository;
    }

    public E findById(I id){
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Entity with id: %d not found!", id))
        );
    }

    public E addNew(E entity){
        if(entity.getId() != null){
            throw new NewEntityIdIsNotNullException();
        }
        return repository.save(entity);
    }

    public void deleteById(I id){
        try {
            repository.deleteById(id);
        } catch (IllegalArgumentException e){
            throw new EntityNotFoundException(String.format("Deletion failed - id: %s not found!", id));
        }
    }

    public abstract E update(BaseDto dto);
}
