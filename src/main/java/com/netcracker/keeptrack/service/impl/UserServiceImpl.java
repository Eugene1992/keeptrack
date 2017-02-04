package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Gender;
import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Request;
import com.netcracker.keeptrack.model.RequestStatus;
import com.netcracker.keeptrack.model.Role;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.UserService;
import com.netcracker.keeptrack.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UserService} interface that provides methods for User
 * entity business logic.
 *
 * @see UserService
 * @see User
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Adds a new user to the system.
     * A new task is created on the basis of the data parsed by {@link UserDTO} object.
     * Data is previously validated by UserValidator.
     * @see UserService#addUser
     *
     * @param userDTO object which contains information about the new user
     */
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

    /**
     * Deletes the user by specified identifier.
     * @see UserService#deleteUser
     *
     * @param id deleted user identifier
     */
    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    /**
     * Returns the user by specified id.
     * @see UserService#getUserById
     *
     * @param id of the required user
     * @return specified user
     */
    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    /**
     * Returns the user by specified name.
     * @see UserService#getUserByUsername
     *
     * @param username of the required user
     * @return specified user
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    /**
     * Updates the specified user.
     * @see UserService#updateUser
     *
     * @param userDTO object which contains updated user information
     */
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

    /**
     * Returns all users in the system.
     * @see UserService#getAllUsers
     *
     * @return list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns employees who are not involved in one of the projects.
     * @see UserService#getFreeEmployees
     *
     * @return list of free employees
     */
    @Override
    public List<User> getFreeEmployees() {
        return userRepository.getFreeEmployees();
    }

    /**
     * Returns all employees in the system.
     * @see UserService#getAllEmployees
     *
     * @return list of all employees
     */
    @Override
    public List<User> getAllEmployees() {
        return userRepository.getAllEmployees();
    }

    /**
     * Returns managers who are not involved in one of the projects.
     * @see UserService#getFreeManagers
     *
     * @return list of free managers
     */
    @Override
    public List<User> getFreeManagers() {
        return userRepository.getFreeManagers();
    }

    /**
     * Returns all managers in the system.
     * @see UserService#getAllManagers
     *
     * @return list of all managers
     */
    @Override
    public List<User> getAllManagers() {
        return userRepository.getAllManagers();
    }

    /**
     * Deletes employee from specified project.
     * @see UserService#deleteEmployeeFormProject
     *
     * @param employeeId deleted employee identifier
     */
    @Override
    public void deleteEmployeeFormProject(String employeeId) {
        Integer id = Integer.parseInt(employeeId);
        User employee = userRepository.findOne(id);
        employee.setProject(null);
        userRepository.save(employee);
    }

    /**
     * Adds employee to specified project.
     * @see UserService#addEmployeeToProject
     *
     * @param employeeId identifier of the added employee
     * @param projectName identifier of the project in which employee will be added
     */
    @Override
    public void addEmployeeToProject(String employeeId, String projectName) {
        Integer id = Integer.parseInt(employeeId);
        User employee = userRepository.findOne(id);
        Project project = projectRepository.getProjectByName(projectName);
        employee.setProject(project);
        userRepository.save(employee);
    }

    /**
     * Returns user tasks by specified status.
     * @param user specified user
     * @param status specified status
     * @return list of the filtered tasks
     */
    @Override
    public List<Task> getUserTasksByStatus(User user, TaskStatus status) {
        return user.getProject().getSprints().stream()
                .flatMap(sprint -> sprint.getTasks().stream())
                .filter(task -> task.getAssigner().equals(user)
                             && task.getStatus() == status)
                .collect(Collectors.toList());
    }

    /**
     * Returns the latest hired employees.
     *
     * @param limit of employees
     * @return list of latest hired employees
     */
    @Override
    public List<Task> getLatestHiredEmployees(Integer limit) {
        return userRepository.getLatestHiredEmployees(new PageRequest(0, limit));
    }

    /**
     * Returns user requests by specified status.
     * @param user specified user
     * @param status specified status
     * @return list of the filtered requests
     */
    @Override
    public List<Request> getUserRequestsByStatus(User user, RequestStatus status) {
        return user.getAssignedRequests().stream()
                .filter(request -> request.getAssigner().equals(user)
                        && request.getStatus() == status)
                .collect(Collectors.toList());
    }
}
