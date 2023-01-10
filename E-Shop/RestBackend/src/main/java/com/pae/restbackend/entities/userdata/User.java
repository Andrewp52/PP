package com.pae.restbackend.entities.userdata;

import com.pae.restbackend.entities.dtobuilders.UserDtoBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.pae.restbackend.entities.ToogleableEntity;
import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends ToogleableEntity implements UserDetails {
    @Column(name = "username")
    private String userName;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "users_phones",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id")
    )
    private Set<Phone> phones;
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "users_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return userName.equals(user.userName) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, firstName, lastName, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles != null){
            return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getEnabled();
    }

    @Override
    public UserDtoBuilder dtoBuilder() {
        return new UserDtoBuilder(this);
    }


}
