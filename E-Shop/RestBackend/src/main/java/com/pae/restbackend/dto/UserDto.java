package com.pae.restbackend.dto;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends ToggleableDto{
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    private Set<AbstractDto> phones;
    private Set<AbstractDto> roles;
    private Set<AbstractDto> addresses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDto userDto = (UserDto) o;
        return userName.equals(userDto.userName) && firstName.equals(userDto.firstName) && lastName.equals(userDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, firstName, lastName);
    }



}
