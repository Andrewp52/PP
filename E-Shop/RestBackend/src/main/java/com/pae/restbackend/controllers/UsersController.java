package com.pae.restbackend.controllers;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.entities.userdata.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyRole({'USER'})")
public class UsersController {
    @GetMapping("/{id}")
    public AbstractDto getUserProfile(@PathVariable(name = "id", required = true) Long id, Authentication authentication){
        User u = getUserFromAuth(authentication);
        if(!u.getId().equals(id)){
            return null;
        }
        return buildProfileDto(u);
    }

    @PutMapping("/{id}/user")
    public AbstractDto updateUserMainData(@PathVariable(name = "id", required = true) Long id, Authentication authentication){
        User u = getUserFromAuth(authentication);
        if(!u.getId().equals(id)){
            return null;
        }
        return null;
    }

    private AbstractDto buildProfileDto(User u){
        return u.dtoBuilder().withRoles().withPhones().withAddresses().build();
    }
    private User getUserFromAuth(Authentication authentication){
        return (User) authentication.getPrincipal();
    }
}
