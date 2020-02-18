package com.example.demo.repository;

import com.example.demo.models.Medicine;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    public List<User> findAll();
    public User save(User user);
    public Optional<User> findById(Long id);
    public Optional<User> findByUsername(String username);


}
