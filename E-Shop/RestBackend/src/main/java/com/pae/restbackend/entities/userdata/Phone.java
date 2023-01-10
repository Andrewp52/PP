package com.pae.restbackend.entities.userdata;

import com.pae.restbackend.dto.BaseDto;
import com.pae.restbackend.dto.PhoneDto;
import com.pae.restbackend.entities.BaseEntity;
import com.pae.restbackend.entities.dtobuilders.BaseDtoBuilder;
import com.pae.restbackend.entities.dtobuilders.PhoneDtoBuilder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "phones")
@Getter
@Setter
public class Phone extends BaseEntity {
    @Column(name = "phone")
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "users_phones",
            joinColumns = @JoinColumn(name = "phone_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Phone phone1 = (Phone) o;
        return phone.equals(phone1.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phone);
    }

    @Override
    public PhoneDtoBuilder dtoBuilder() {
        return new PhoneDtoBuilder(this);
    }


}
