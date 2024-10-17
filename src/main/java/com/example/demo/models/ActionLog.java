package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ActionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actionType; // Тип дії: додавання, видалення, видача
    private String description; // Опис дії
    private LocalDateTime timestamp; // Час виконання дії

    public ActionLog() {}

    public ActionLog(String actionType, String description) {
        this.actionType = actionType;
        this.description = description;
        this.timestamp = LocalDateTime.now(); // Встановлюємо поточний час
    }

    // Геттери і сеттери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
