package com.pae.restbackend.services;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.dto.UserDto;
import com.pae.restbackend.entities.userdata.Role;
import com.pae.restbackend.entities.userdata.User;
import com.pae.restbackend.exceptions.EntityNotFoundException;
import com.pae.restbackend.exceptions.NewEntityIdIsNotNullException;
import com.pae.restbackend.exceptions.UpdatableEntityIdIsNullException;
import com.pae.restbackend.repositories.BaseRepository;
import com.pae.restbackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService extends AbstractService<User, Long> implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final AbstractService<Role, Long> roleService;
    @Autowired
    public UserService(BaseRepository<User, Long> repository, PasswordEncoder passwordEncoder, AbstractService<Role, Long> roleService) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User addNew(User entity) {
        if(entity.getId() != null){
            throw new NewEntityIdIsNotNullException("New user id must be null!");
        }
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setEnabled(true);
        Role r = ((RoleService)roleService).findByRole("USER");
        entity.setRoles(new HashSet<>());
        entity.getRoles().add(r);
        return repository.save(entity);
    }

    @Override
    public User update(AbstractDto dto) {
        if(dto.getId() == null){
            throw new UpdatableEntityIdIsNullException("Updatable user is mandatory!");
        }
        User u = repository.findById(dto.getId()).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with id: %d not found!", dto.getId()))
        );
        UserDto d = (UserDto) dto;
        u.setUserName(d.getUserName());
        u.setFirstName(d.getFirstName());
        u.setLastName(d.getLastName());
        return repository.save(u);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ((UsersRepository)repository).findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
    }

}
