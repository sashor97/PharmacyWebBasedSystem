package com.example.demo.service;

import com.example.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    public List<User> findAll();
    public User save(User user);
    public Optional<User> findById(Long id);
    public Optional<User> findByUsername(String username);
}
