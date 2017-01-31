package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.web.dto.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * User interface that describes methods for User entity business logic.
 *
 * @see User
 */
public interface UserService {

    /**
     * Adds a new user to the system.
     * This function is only available for the administrator.
     *
     * @param userDTO object which contains information about the new user
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void addUser(UserDTO userDTO);

    /**
     * Deletes the user by specified identifier.
     * This function is only available for the administrator.
     *
     * @param id deleted user identifier
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void deleteUser(Integer id);

    /**
     * Returns the user by specified id.
     * This function is only available for the administrator.
     *
     * @param id of the required user
     * @return specified user
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    User getUserById(Integer id);

    /**
     * Returns the user by specified name.
     * This function is only available for the administrator.
     *
     * @param username of the required user
     * @return specified user
     */
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'PM', 'EMPLOYEE')")
    User getUserByUsername(String username);

    /**
     * Updates the specified user.
     * This function is only available for the administrator.
     *
     * @param userDTO object which contains updated user information
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void updateUser(UserDTO userDTO);

    /**
     * Returns all users in the system.
     * This function is only available for the administrator.
     *
     * @return list of all users
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<User> getAllUsers();

    /**
     * Returns employees who are not involved in one of the projects.
     * This function is only available for the administrator.
     *
     * @return list of free employees
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<User> getFreeEmployees();

    /**
     * Returns managers who are not involved in one of the projects.
     * This function is only available for the administrator.
     *
     * @return list of free managers
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<User> getFreeManagers();

    /**
     * Returns all employees in the system.
     * This function is only available for the administrator.
     *
     * @return list of all employees
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<User> getAllEmployees();

    /**
     * Returns all managers in the system.
     * This function is only available for the administrator.
     *
     * @return list of all managers
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<User> getAllManagers();

    /**
     * Deletes employee from specified project.
     * This function is only available for the administrator.
     *
     * @param employeeId deleted employee identifier
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void deleteEmployeeFormProject(String employeeId);

    /**
     * Adds employee to specified project.
     * This function is only available for the administrator.
     *
     * @param employeeId identifier of the added employee
     * @param projectName identifier of the project in which employee will be added
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void addEmployeeToProject(String employeeId, String projectName);

    /**
     * Returns user tasks by specified status.
     * @param user specified user
     * @param status specified status
     * @return list of the filtered tasks
     */
    List<Task> getUserTasksByStatus(User user, TaskStatus status);
}
