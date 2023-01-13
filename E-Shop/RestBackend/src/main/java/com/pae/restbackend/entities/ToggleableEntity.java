package com.pae.restbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class ToggleableEntity extends AbstractEntity {
    @Column(name = "enabled")
    private Boolean enabled;

    public void toggle(){
        this.enabled = !this.enabled;
    }

}
