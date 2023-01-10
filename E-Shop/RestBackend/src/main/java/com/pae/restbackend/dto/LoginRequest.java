package com.pae.restbackend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LoginRequest {
    private String userName;
    private String password;

}
