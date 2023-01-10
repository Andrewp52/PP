package com.pae.restbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto extends BaseDto{
    private String city;
    private String street;
    private String building;
    private String aptOffice;

    private Set<BaseDto> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddressDto that = (AddressDto) o;
        return city.equals(that.city) && street.equals(that.street) && building.equals(that.building) && Objects.equals(aptOffice, that.aptOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), city, street, building, aptOffice);
    }
}
