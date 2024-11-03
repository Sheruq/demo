package com.example.demo.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password; // Поле для пароля

    @ManyToMany
    private List<Arina> borrowedBooks = new ArrayList<>(); // Ініціалізуємо список

    // Конструктори
    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Геттери та сеттери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Arina> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Arina> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    // Додавання книги до списку
    public void addBook(Arina book) {
        this.borrowedBooks.add(book);
    }

    public void removeBook(Arina book) {
        this.borrowedBooks.remove(book);
    }
}
