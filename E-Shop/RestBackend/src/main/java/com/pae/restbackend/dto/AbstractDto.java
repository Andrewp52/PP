package com.pae.restbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDto {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDto abstractDto = (AbstractDto) o;
        return Objects.equals(id, abstractDto.id) && Objects.equals(created, abstractDto.created) && Objects.equals(modified, abstractDto.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, modified);
    }
}
