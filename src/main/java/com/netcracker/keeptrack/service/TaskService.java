package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.web.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface that describes methods for Task entity business logic.
 *
 * @see Task
 */
@Service
public interface TaskService {

    void addTask(TaskDTO taskDTO);

    void deleteTask(Integer id);

    Task getTaskById(Integer id);

    Task getTaskByName(String name);

    void updateTask(TaskDTO taskDTO);

    List<Task> getAllTasks();

    void deleteTaskFromSprint(String taskId);

    void addTaskToSprint(String name, String endDate, String estimate,
                         String assignerId, String description, String sprintId);
}
