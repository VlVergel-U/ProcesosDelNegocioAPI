package com.procesos.parcial.controller;

import com.procesos.parcial.model.Editorial;
import com.procesos.parcial.model.User;
import com.procesos.parcial.model.dto.Request;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody @Valid Request<User> request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request.getObject()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@RequestBody @Valid Request<User> request, @PathVariable @Min(1) Long id) {
        return ResponseEntity.ok().body(userService.updateUser(request.getObject(), id));
    }

    @GetMapping
    public ResponseEntity<Response> allUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }
}