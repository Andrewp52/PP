package com.pae.restbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class ToggleableDto extends BaseDto{
    private Boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ToggleableDto that = (ToggleableDto) o;
        return enabled.equals(that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), enabled);
    }
}
