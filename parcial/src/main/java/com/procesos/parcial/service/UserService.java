package com.procesos.parcial.service;

import com.procesos.parcial.model.User;
import com.procesos.parcial.model.dto.Response;

import java.util.List;


public interface UserService {
    Response createUser(User user);
    Response updateUser(User user, Long id);
    Response getUserById(Long id);
    Response findAllUsers();
}