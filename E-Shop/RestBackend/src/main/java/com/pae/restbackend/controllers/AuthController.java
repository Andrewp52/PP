package com.pae.restbackend.controllers;

import com.pae.restbackend.config.security.jwt.JWTUtils;
import com.pae.restbackend.dto.BaseDto;
import com.pae.restbackend.dto.LoginRequest;
import com.pae.restbackend.entities.dtobuilders.UserDtoBuilder;
import com.pae.restbackend.entities.userdata.User;
import com.pae.restbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(user.dtoBuilder().withRoles().build());
    }

    @PostMapping("/logout")
    public void logoutUser(){

    }

    @PostMapping("/register")
    public BaseDto registerNewUser(){
        return null;
    }
}
