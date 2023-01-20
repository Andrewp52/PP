package com.pae.restbackend.controllers;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.dto.AddressDto;
import com.pae.restbackend.dto.PhoneDto;
import com.pae.restbackend.dto.UserDto;
import com.pae.restbackend.entities.userdata.Address;
import com.pae.restbackend.entities.userdata.Phone;
import com.pae.restbackend.entities.userdata.User;
import com.pae.restbackend.exceptions.EntityNotFoundException;
import com.pae.restbackend.exceptions.PhoneNumberEmptyException;
import com.pae.restbackend.services.AbstractService;
import com.pae.restbackend.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RolesAllowed("ROLE_USER")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    @GetMapping
    public AbstractDto getUserProfile(Authentication authentication){
        User u = getUserFromAuth(authentication);
        return buildProfileDto(userService.findById(u.getId()));
    }

    @PutMapping
    public AbstractDto updateUserMainData(Authentication authentication, @RequestBody(required = true) UserDto dto){
        User u = getUserFromAuth(authentication);
        dto.setId(u.getId());
        u = userService.update(dto);
        return u.dtoBuilder().build();
    }

    @PostMapping("/phone")
    public AbstractDto addNewUserPhone(Authentication authentication, @RequestBody(required = true) PhoneDto dto){
        Phone p = userService.addPhone(getUserFromAuth(authentication).getId(), dto);
        return p == null? null : p.dtoBuilder().build();
    }
    @PutMapping("/phone")
    public AbstractDto updateUserPhone(Authentication authentication, @RequestBody(required = true) PhoneDto dto){
        Long userId = getUserFromAuth(authentication).getId();
        Phone p = this.userService.updatePhone(userId, dto);
        return p == null ? null : p.dtoBuilder().build();
    }

    @DeleteMapping("/phone/{id}")
    public void deletePhoneWithId(
            @PathVariable(name = "id", required = true) Long phoneId,
            Authentication authentication,
            HttpServletResponse response
    ){
        Long userId = getUserFromAuth(authentication).getId();
        this.userService.deletePhone(userId, phoneId);
        response.setStatus(HttpStatus.OK.value());
    }

    @PostMapping("/address")
    public AbstractDto addNewUserAddress(Authentication authentication, @RequestBody(required = true) AddressDto dto){
        Long userId = (getUserFromAuth(authentication)).getId();
        Address added = this.userService.addAddress(userId, dto);
        return added.dtoBuilder().build();
    }

    @PutMapping("/address")
    public AbstractDto updateUserAddress(Authentication authentication, @RequestBody(required = true) AddressDto dto){
        Long userId = getUserFromAuth(authentication).getId();
        Address a = this.userService.updateAddress(userId, dto);
        return a.dtoBuilder().build();
    }

    @DeleteMapping("/address/{id}")
    public void deleteAddressWithId(
            @PathVariable(name = "id", required = true) Long addressId,
            Authentication authentication,
            HttpServletResponse response){
        Long userId = getUserFromAuth(authentication).getId();
        this.userService.deleteAddress(userId, addressId);
        response.setStatus(HttpStatus.OK.value());
    }

    private AbstractDto buildProfileDto(User u){
        return u.dtoBuilder().withRoles().withPhones().withAddresses().build();
    }
    private User getUserFromAuth(Authentication authentication){
        return (User) authentication.getPrincipal();
    }

    // TODO: Unite userdata exceptions.
    @ExceptionHandler({EntityNotFoundException.class, PhoneNumberEmptyException.class})
    public void handleExceptions(RuntimeException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.EXPECTATION_FAILED.value());
    }
}
