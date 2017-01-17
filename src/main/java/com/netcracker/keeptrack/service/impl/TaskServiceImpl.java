package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.SprintRepository;
import com.netcracker.keeptrack.repository.TaskRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Dsdasdsdasa ds ads asd sa.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Override
    public void addTask(Task task) {
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.delete(id);
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findOne(id);
    }

    @Override
    public void editTask(Task task) {
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void deleteTaskFromSprint(String taskId) {
        Integer id = Integer.parseInt(taskId);
        Task task = taskRepository.findOne(id);
        task.setSprint(null);
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void addTaskToSprint(String name, String endDate, String estimate,
                                String assignerId, String description, String sprintId) {
        Task task = new Task();
        task.setName(name);
        LocalDate taskEndDate = LocalDate.parse(endDate);
        task.setEndDate(taskEndDate);
        Integer taskEstimate = Integer.parseInt(estimate);
        task.setEstimate(taskEstimate);
        task.setDescription(description);
        Integer taskAssignerId = Integer.parseInt(assignerId);
        User assigner = userRepository.findOne(taskAssignerId);
        task.setAssigner(assigner);
        Integer taskSprintId = Integer.parseInt(assignerId);
        Sprint sprint = sprintRepository.findOne(taskSprintId);
        task.setSprint(sprint);
        taskRepository.saveAndFlush(task);
    }
}
