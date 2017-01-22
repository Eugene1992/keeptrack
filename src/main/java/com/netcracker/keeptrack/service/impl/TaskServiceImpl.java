package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.SprintRepository;
import com.netcracker.keeptrack.repository.TaskRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.TaskService;
import com.netcracker.keeptrack.web.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        Integer sprintId = Integer.valueOf(taskDTO.getSprintId());
        Sprint taskSprint = sprintRepository.findOne(sprintId);
        task.setSprint(taskSprint);
        Integer assignerId = Integer.valueOf(taskDTO.getAssignerId());
        User taskAssigner = userRepository.findOne(assignerId);
        task.setAssigner(taskAssigner);
        Integer creatorId = Integer.valueOf(taskDTO.getCreatorId());
        User taskCreator = userRepository.findOne(creatorId);
        task.setCreator(taskCreator);
        task.setName(taskDTO.getName());
        task.setStartDate(LocalDate.parse(taskDTO.getStartDate()));
        task.setEndDate(LocalDate.parse(taskDTO.getEndDate()));
        task.setStatus(TaskStatus.valueOf(taskDTO.getStatus()));
        task.setDescription(taskDTO.getDescription());
        task.setEstimate(Integer.parseInt(taskDTO.getEstimate()));
        taskRepository.save(task);
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
    public Task getTaskByName(String name) {
        return taskRepository.getTaskByName(name);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Integer taskId = Integer.valueOf(taskDTO.getId());
        Task task = taskRepository.findOne(taskId);
        Integer taskSprintId = Integer.valueOf(taskDTO.getSprintId());
        Sprint taskSprint = sprintRepository.findOne(taskSprintId);
        task.setSprint(taskSprint);
        Integer assignerId = Integer.valueOf(taskDTO.getAssignerId());
        User taskAssigner = userRepository.findOne(assignerId);
        task.setAssigner(taskAssigner);
        Integer creatorId = Integer.valueOf(taskDTO.getCreatorId());
        User taskCreator = userRepository.findOne(creatorId);
        task.setCreator(taskCreator);
        task.setName(taskDTO.getName());
        task.setStartDate(LocalDate.parse(taskDTO.getStartDate()));
        task.setEndDate(LocalDate.parse(taskDTO.getEndDate()));
        task.setStatus(TaskStatus.valueOf(taskDTO.getStatus()));
        task.setDescription(taskDTO.getDescription());
        task.setEstimate(Integer.parseInt(taskDTO.getEstimate()));
        taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
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
