package com.example.demo.repo;

import com.example.demo.models.Arina;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AriRepo extends CrudRepository<Arina, Long> {
    List<Arina> findByTitleContainingIgnoreCase(String title); // Метод для пошуку за назвою книги
    Iterable<Arina> findByTitleContainingOrAuthorContaining(String title, String author);
}



