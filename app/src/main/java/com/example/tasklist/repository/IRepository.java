package com.example.tasklist.repository;

import com.example.tasklist.model.Task;

import java.util.List;
import java.util.UUID;

public interface IRepository {
    List<Task> getTasks();

    void create(Task task);

    Task read(UUID id);

    void update(Task task);

    void delete(Task task);
}
