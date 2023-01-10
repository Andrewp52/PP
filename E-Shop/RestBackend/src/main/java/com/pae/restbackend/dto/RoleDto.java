package com.pae.restbackend.dto;

import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto extends BaseDto {
    private String role;
    private String description;
    private Set<BaseDto> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoleDto roleDto = (RoleDto) o;
        return role.equals(roleDto.role) && Objects.equals(description, roleDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, description);
    }

}
