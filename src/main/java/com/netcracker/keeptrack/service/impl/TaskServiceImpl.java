package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.repository.TaskRepository;
import com.netcracker.keeptrack.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    public void addTask(Task task) {
        repository.saveAndFlush(task);
    }

    @Override
    public void deleteTask(Integer id) {
        repository.delete(id);
    }

    @Override
    public Task getTaskById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void editTask(Task task) {
        repository.saveAndFlush(task);
    }
}
