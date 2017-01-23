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
 * Implementation of {@link TaskService} interface that provides methods for Task
 * entity business logic.
 *
 * @see TaskService
 * @see Sprint
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SprintRepository sprintRepository;

    /**
     * Adds a new task to the system.
     * A new task is created on the basis of the data parsed by {@link TaskDTO} object.
     * Data is previously validated by TaskValidator.
     * @see TaskService#addTask
     *
     * @param taskDTO object which contains information about the new task
     */
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

    /**
     * Deletes the task by specified identifier.
     * @see TaskService#deleteTask
     *
     * @param id deleted task identifier
     */
    @Override
    public void deleteTask(Integer id) {
        taskRepository.delete(id);
    }

    /**
     * Returns the task by specified id.
     * @see TaskService#getTaskById
     *
     * @param id of the required task
     * @return specified task
     */
    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findOne(id);
    }

    /**
     * Returns the task by specified name.
     * @see TaskService#getTaskByName
     *
     * @param name of the required task
     * @return specified task
     */
    @Override
    public Task getTaskByName(String name) {
        return taskRepository.getTaskByName(name);
    }

    /**
     * Updates the specified task.
     * @see TaskService#updateTask
     *
     * @param taskDTO object which contains updated task information
     */
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

    /**
     * Returns all tasks in the system.
     * @see TaskService#getAllTasks
     *
     * @return list of all employees
     */
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTaskFromSprint(Integer taskId) {
        Task task = taskRepository.findOne(taskId);
        task.setSprint(null);
        taskRepository.saveAndFlush(task);
    }

    /**
     * Check whether there is a sprint with the specified name.
     * @see TaskService#addTaskToSprint
     *
     * @param name of specified sprint
     */
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
