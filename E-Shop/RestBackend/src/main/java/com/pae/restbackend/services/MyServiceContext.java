package com.pae.restbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MyServiceContext {
    private static final Map<Class, AbstractService> serviceMap = new HashMap<>();;

    private MyServiceContext(){}

    @Autowired
    public void setServiceMap(Set<AbstractService> map){
        map.forEach(v -> serviceMap.put(v.getClass(), v));
    }

    static AbstractService getService(Class<? extends AbstractService> cl){
        return serviceMap.get(cl);
    }

}
