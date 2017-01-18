package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link UserService} interface that provides methods for Task
 * entity business logic.
 *
 * @see User
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public void editUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getFreeEmployees() {
        return userRepository.getFreeEmployees();
    }

    @Override
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getFreeManagers() {
        return userRepository.getFreeManagers();
    }

    @Override
    public List<User> getAllManagers() {
        return null;
    }

    @Override
    public void deleteEmployeeFormProject(String employeeId) {
        Integer id = Integer.parseInt(employeeId);
        User employee = userRepository.findOne(id);
        employee.setProject(null);
        userRepository.save(employee);
    }

    @Override
    public void addEmployeeToProject(String employeeId, String projectName) {
        Integer id = Integer.parseInt(employeeId);
        User employee = userRepository.findOne(id);
        Project project = projectRepository.getProjectByName(projectName);
        employee.setProject(project);
        userRepository.save(employee);
    }
}
