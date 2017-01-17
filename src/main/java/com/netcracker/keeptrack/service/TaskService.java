package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Task;
import org.springframework.stereotype.Service;

/**
 * Service interface that describes methods for Task entity business logic.
 *
 * @see Task
 */
@Service
public interface TaskService {

    void addTask(Task task);

    void deleteTask(Integer id);

    Task getTaskById(Integer id);

    void editTask(Task task);

    void deleteTaskFromSprint(String taskId);

    void addTaskToSprint(String name, String endDate, String estimate,
                         String assignerId, String description, String sprintId);
}
