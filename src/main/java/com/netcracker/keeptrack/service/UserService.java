package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.User;

import java.util.List;

/**
 * User interface that describes methods for User entity business logic.
 *
 * @see User
 */
public interface UserService {

    void addUser(User user);

    void deleteUser(Integer id);

    User getUserById(Integer id);

    void editUser(User user);

    List<User> getFreeEmployees();

    List<User> getFreeManagers();

}
