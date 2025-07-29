package com.api.redis.controllers;

import com.api.redis.doa.UserDao;
import com.api.redis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    // Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);
    }

    // Get Single User
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userDao.get(userId);
    }

    // Get All Users
    @GetMapping
    public List<User> getAll() {
        Map<Object, Object> all = userDao.findAll();
        Collection<Object> values = all.values();
        return values.stream().map(value -> (User) value).collect(Collectors.toList());
    }

    // Delete User
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userDao.delete(userId);
    }
}
