package com.pae.restbackend.services;

import com.pae.restbackend.dto.BaseDto;
import com.pae.restbackend.entities.userdata.User;
import com.pae.restbackend.repositories.BaseRepository;
import com.pae.restbackend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long> implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(BaseRepository<User, Long> repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User update(BaseDto dto) {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ((UsersRepository)repository).findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
    }

}
