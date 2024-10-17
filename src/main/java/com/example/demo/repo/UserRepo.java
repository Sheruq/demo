package com.example.demo.repo;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findByNameContainingIgnoreCase(String name); // Метод для пошуку за іменем користувача
}
