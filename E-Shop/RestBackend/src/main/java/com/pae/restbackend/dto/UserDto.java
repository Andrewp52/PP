package com.pae.restbackend.dto;

import com.pae.restbackend.entities.userdata.User;
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

    private Set<BaseDto> phones;
    private Set<BaseDto> roles;
    private Set<BaseDto> addresses;

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
