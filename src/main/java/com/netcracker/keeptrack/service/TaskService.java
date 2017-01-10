package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Task;

public interface TaskService {

    void addTask(Task task);

    void deleteTask(Integer id);

    Task getTaskById(Integer id);

    void editTask(Task task);
}
