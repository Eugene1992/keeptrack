package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.web.dto.UserDTO;

import java.util.List;

/**
 * User interface that describes methods for User entity business logic.
 *
 * @see User
 */
public interface UserService {

    void addUser(UserDTO userDTO);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    User getUserByUsername(String username);

    void updateUser(UserDTO userDTO);

    List<User> getAllUsers();

    List<User> getFreeEmployees();

    List<User> getAllEmployees();

    List<User> getFreeManagers();

    List<User> getAllManagers();

    void deleteEmployeeFormProject(String employeeId);

    void addEmployeeToProject(String employeeId, String projectName);
}
