package com.api.Films.service;

import com.api.Films.entity.User;
import java.util.ArrayList;
import java.util.Optional;

public interface UserService {
    ArrayList<User> getUser();

    User saveUser(User user);

    Optional<User> getById(Long id);

    Optional<User> findByUsername(String username);
}
