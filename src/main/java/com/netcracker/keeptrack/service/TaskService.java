package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.web.dto.TaskDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface that describes methods for Task entity business logic.
 *
 * @see Task
 */
@Service
public interface TaskService {

    /**
     * Adds a new task to the system.
     * This function is only available for the administrator.
     *
     * @param taskDTO object which contains information about the new task
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void addTask(TaskDTO taskDTO);

    /**
     * Deletes the task by specified identifier.
     * This function is only available for the administrator.
     *
     * @param id deleted task identifier
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void deleteTask(Integer id);

    /**
     * Returns the task by specified id.
     * This function is only available for the administrator.
     * The employee and project manager only know about the tasks that they have created
     * or that have been assigned to them.
     *
     * @param id of the required task
     * @return specified task
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    Task getTaskById(Integer id);

    /**
     * Returns the task by specified name.
     * This function is only available for the administrator.
     * The employee and project manager only know about the tasks that they have created
     * or that have been assigned to them.
     *
     * @param name of the required task
     * @return specified task
     */
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'PM')")
    Task getTaskByName(String name);

    /**
     * Updates the specified task.
     * This function is only available for the administrator.
     * The employee and project manager only know about the tasks that they have created
     * or that have been assigned to them.
     *
     * @param taskDTO object which contains updated task information
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void updateTask(TaskDTO taskDTO);

    /**
     * Returns all tasks in the system.
     * This function is only available for the administrator.
     * The employee and project manager only know about the tasks that they have created
     * or that have been assigned to them.
     *
     * @return list of all employees
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<Task> getAllTasks();

    /**
     * Deletes the task from current project by specified identifier.
     * This function is only available for the administrator in project profile.
     *
     * @param taskId deleted task identifier
     */
    void deleteTaskFromSprint(Integer taskId);

    void addTaskToSprint(TaskDTO taskDTO);

    /**
     * Change task status.
     * Used when user accept, reject, close task.
     *
     * @param taskId task id
     * @param status new task status
     */
    void changeTaskStatus(Integer taskId, TaskStatus status);
}
