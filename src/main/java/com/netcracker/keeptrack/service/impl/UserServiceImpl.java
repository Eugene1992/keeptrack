package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Gender;
import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Role;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.UserService;
import com.netcracker.keeptrack.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void addUser(UserDTO userDTO) {
        User user = new User();
        Integer projectId = Integer.valueOf(userDTO.getProjectId());
        Project project = projectRepository.findOne(projectId);
        user.setProject(project);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setSalary(Integer.parseInt(userDTO.getSalary()));
        user.setEmail(userDTO.getEmail());
        user.setGender(Gender.valueOf(userDTO.getGender()));
        user.setBirthday(LocalDate.parse(userDTO.getBirthday()));
        user.setHireDay(LocalDate.parse(userDTO.getHireDay()));
        userRepository.save(user);
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
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Integer userId = Integer.valueOf(userDTO.getId());
        User user = userRepository.findOne(userId);
        Integer projectId = Integer.valueOf(userDTO.getProjectId());
        Project project = projectRepository.findOne(projectId);
        user.setProject(project);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setSalary(Integer.parseInt(userDTO.getSalary()));
        user.setEmail(userDTO.getEmail());
        user.setGender(Gender.valueOf(userDTO.getGender()));
        user.setBirthday(LocalDate.parse(userDTO.getBirthday()));
        user.setHireDay(LocalDate.parse(userDTO.getHireDay()));
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
        return userRepository.getAllManagers();
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
