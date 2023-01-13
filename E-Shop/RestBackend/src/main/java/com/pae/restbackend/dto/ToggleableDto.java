package com.pae.restbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class ToggleableDto extends AbstractDto {
    private Boolean enabled;

}
