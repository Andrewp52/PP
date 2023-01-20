package com.pae.restbackend.entities.userdata;

import com.pae.restbackend.entities.AbstractEntity;
import com.pae.restbackend.entities.dtobuilders.AddressDtoBuilder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address extends AbstractEntity {
    @Column(name = "region")
    private String region;

    @Column(name = "area")
    private String area;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "building")
    private String building;
    @Column(name = "aptoffice")
    private String aptOffice;

    @ManyToMany
    @JoinTable(
            name = "users_addresses",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return city.equals(address.city) && street.equals(address.street) && building.equals(address.building) && Objects.equals(aptOffice, address.aptOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), city, street, building, aptOffice);
    }


    @Override
    public AddressDtoBuilder dtoBuilder() {
        return new AddressDtoBuilder(this);
    }
}
