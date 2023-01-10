package com.pae.restbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class ToogleableEntity extends BaseEntity{
    @Column(name = "enabled")
    private Boolean enabled;

    public void toggle(){
        this.enabled = !this.enabled;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ToogleableEntity that = (ToogleableEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
