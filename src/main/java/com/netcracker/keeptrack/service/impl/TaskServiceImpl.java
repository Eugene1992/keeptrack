package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Request;
import com.netcracker.keeptrack.model.RequestStatus;
import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskEstimateRequest;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.RequestRepository;
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

    @Autowired
    private RequestRepository requestRepository;

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
     * @param taskDTO of specified task
     */
    @Override
    public void addTaskToSprint(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        LocalDate taskStartDate = LocalDate.parse(taskDTO.getStartDate());
        task.setStartDate(taskStartDate);
        Integer taskEstimate = Integer.parseInt(taskDTO.getEstimate());
        task.setEstimate(taskEstimate);
        task.setStatus(TaskStatus.ASSIGNED);
        task.setDescription(taskDTO.getDescription());
        Integer taskCreatorId = Integer.parseInt(taskDTO.getCreatorId());
        User creator = userRepository.findOne(taskCreatorId);
        task.setCreator(creator);
        Integer taskAssignerId = Integer.parseInt(taskDTO.getAssignerId());
        User assigner = userRepository.findOne(taskAssignerId);
        task.setAssigner(assigner);
        Integer sprintId = Integer.parseInt(taskDTO.getSprintId());
        Sprint sprint = sprintRepository.findOne(sprintId);
        task.setSprint(sprint);
        taskRepository.saveAndFlush(task);
    }

    /**
     * Change task status.
     * Used when user accept, reject, close task.
     *
     * @param taskId task id
     * @param status new task status
     */
    @Override
    public void changeTaskStatus(Integer taskId, TaskStatus status) {
        Task task = taskRepository.findOne(taskId);
        task.setStatus(status);
        taskRepository.save(task);
    }

    /**
     * Close selected task.
     * Used when user complete the task.
     *
     * @param taskId task id
     */
    @Override
    public void closeTask(Integer taskId) {
        Task task = taskRepository.findOne(taskId);
        task.setStatus(TaskStatus.CLOSED);
        task.setEndDate(LocalDate.now());
        taskRepository.save(task);
    }

    /**
     * Processes a request to change the estimate time.
     *
     * @param taskId specified task
     * @param tittle specified task tittle
     * @param reqEstimate requested estimate
     * @param description specified task description
     */
    @Override
    public void handleEstimateRequest(Integer taskId, String tittle, Integer reqEstimate, String description) {
        TaskEstimateRequest request = new TaskEstimateRequest();
        Task task = taskRepository.findOne(taskId);
        request.setTittle(tittle);
        request.setAssigner(task.getCreator());
        request.setCreator(task.getAssigner());
        request.setReqEstimate(reqEstimate);
        request.setTask(task);
        request.setDescription(description);
        request.setStatus(RequestStatus.OPENED);
        requestRepository.save(request);
    }

    /**
     * Accepts the specified request.
     * Finds the request for a given id, define the request type and cast the base class.
     * At this point we have only one request type, but in the future it will allow flexibility
     * to expand it with new request types.
     *
     * @param requestId request identifier
     */
    @Override
    public void acceptRequest(Integer requestId) {
        Request request = requestRepository.findOne(requestId);
        if (request instanceof TaskEstimateRequest) {
            TaskEstimateRequest estimateRequest = (TaskEstimateRequest) request;
            Task task = estimateRequest.getTask();
            int requestedEstimate = estimateRequest.getReqEstimate();
            task.setEstimate(requestedEstimate);
            taskRepository.save(task);
            request.setStatus(RequestStatus.ACCEPTED);
        }
        requestRepository.save(request);
    }

    /**
     * Rejects the specified request.
     *
     * @param requestId request identifier
     */
    @Override
    public void rejectRequest(Integer requestId) {
        Request request = requestRepository.findOne(requestId);
        request.setStatus(RequestStatus.REJECTED);
        requestRepository.save(request);
    }
}
