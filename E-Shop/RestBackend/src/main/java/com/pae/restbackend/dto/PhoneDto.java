package com.pae.restbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto extends AbstractDto {
    private String phone;
    private Set<AbstractDto> users;


}
