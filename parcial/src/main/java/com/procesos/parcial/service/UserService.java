package com.procesos.parcial.service;

import com.procesos.parcial.model.User;
import java.util.List;


public interface UserService {
    User createUser(User user);
    User updateUser(User user, Long id);
    User getUserById(Long id);
    List<User> findAllUsers();
}