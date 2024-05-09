package com.api.Films.controller;

import com.api.Films.entity.User;
import com.api.Films.repository.UserRepository;
import com.api.Films.service.UserService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<User> getUser() {
       return this.userService.getUser();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path= "/{id}")
    public Optional<User> getUserById(@PathVariable ("id") Long id) {
        return this.userService.getById(id);
    }
}
