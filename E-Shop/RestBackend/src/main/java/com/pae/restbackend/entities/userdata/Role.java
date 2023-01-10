package com.pae.restbackend.entities.userdata;

import com.pae.restbackend.entities.BaseEntity;
import com.pae.restbackend.entities.dtobuilders.RoleDtoBuilder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseEntity {
    @Column(name = "role")
    private String role;
    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role1 = (Role) o;
        return role.equals(role1.role) && Objects.equals(description, role1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, description);
    }

    @Override
    public RoleDtoBuilder dtoBuilder() {
        return new RoleDtoBuilder(this);
    }

//    public RoleDtoBuilder dtoBuilder() {
//        return new RoleDtoBuilder();
//    }

//    public class RoleDtoBuilder {
//        private RoleDto dto;
//
//        public RoleDtoBuilder() {
//            this.dto = new RoleDto(getRole(), getDescription());
//        }
//
//        public RoleDto build(){
//            return this.dto;
//        }
//    }
}
