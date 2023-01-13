package com.pae.restbackend.entities.entityfactories;

import com.pae.restbackend.dto.AbstractDto;
import com.pae.restbackend.dto.UserDto;
import com.pae.restbackend.entities.userdata.User;
import org.springframework.stereotype.Component;

@Component
public class UserEntityFactory implements EntityFactory<User>{
    @Override
    public User getEntity(AbstractDto dto) {
        UserDto d = (UserDto) dto;
        User u = new User();
        u.setId(d.getId());
        u.setUserName(d.getUserName());
        u.setFirstName(d.getFirstName());
        u.setLastName(d.getLastName());
        u.setPassword(d.getPassword());
        return u;
    }
}
