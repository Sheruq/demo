package com.example.demo.repo;

import com.example.demo.models.ActionLog;
import org.springframework.data.repository.CrudRepository;

public interface ActionLogRepo extends CrudRepository<ActionLog, Long> {
}
