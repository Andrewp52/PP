package com.pae.restbackend.controllers;

import com.pae.restbackend.config.security.jwt.JWTUtils;
import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.dto.LoginRequest;
import com.pae.restbackend.dto.UserDto;
import com.pae.restbackend.entities.entityfactories.EntityFactory;
import com.pae.restbackend.entities.userdata.User;
import com.pae.restbackend.exceptions.NewEntityIdIsNotNullException;
import com.pae.restbackend.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final EntityFactory<User> userFactory;

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
    public ResponseEntity<?> logoutUser(){
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @PostMapping("/register")
    public AbstractDto registerNewUser(@RequestBody(required = true) UserDto dto){
        User registered = userService.addNew(userFactory.getEntity(dto));
        return registered.dtoBuilder().build();
    }

    @ExceptionHandler(AuthenticationException.class)
    public void handleAuthException(AuthenticationException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
//        logger.warn(e.getMessage());
    }

    @ExceptionHandler(NewEntityIdIsNotNullException.class)
    public void handleNotNullIdException(NewEntityIdIsNotNullException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, e.getMessage());
        //        logger.warn(e.getMessage());
    }
}
