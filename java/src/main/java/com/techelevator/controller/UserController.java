package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user/username/{id}")
    public User getUserById(@PathVariable Long id) {
        return userDao.getUserById(id);
    }

    @GetMapping("/user/role/{name}")
    public List<User> getUsersByRole(@PathVariable String name) {
        return userDao.getUsersByRole(name);
    }
        @GetMapping("/user")
        public List<User> getUsers() {
            return userDao.findAll();
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userDao.findByUsername(username);
    }
}
