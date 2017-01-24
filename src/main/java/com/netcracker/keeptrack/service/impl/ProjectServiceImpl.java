package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.ProjectStatus;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.ProjectService;
import com.netcracker.keeptrack.web.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of {@link ProjectService} interface that provides methods for Project
 * entity business logic.
 *
 * @see ProjectService
 * @see Project
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new project to the system.
     * A new project is created on the basis of the data parsed by {@link ProjectDTO} object.
     * Data is previously validated by ProjectValidator.
     * @see ProjectService#addProject
     *
     * @param dto data tranfer object which contains information about the new project
     */
    @Override
    public void addProject(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.parse(dto.getEndDate()));
        project.setDescription(dto.getDescription());
        project.setStatus(ProjectStatus.CREATED);
        projectRepository.saveAndFlush(project);
        Integer managerId = Integer.parseInt(dto.getManagerId());
        User manager = userRepository.findOne(managerId);
        manager.setManagedProject(project);
        userRepository.save(manager);
        for (String employeeId : dto.getEmployees()) {
            Integer parsedId = Integer.parseInt(employeeId);
            User employee = userRepository.findOne(parsedId);
            employee.setProject(project);
            userRepository.save(employee);
        }
    }

    /**
     * Deletes the project by specified identifier.
     * @see ProjectService#deleteProject
     *
     * @param id deleted project identifier
     */
    @Override
    public void deleteProject(Integer id) {
        projectRepository.delete(id);
    }

    /**
     * Returns the project by specified id.
     * @see ProjectService#getProjectById
     *
     * @param id of the required project
     * @return specified project
     */
    @Override
    public Project getProjectById(Integer id) {
        return projectRepository.findOne(id);
    }

    /**
     * Returns the project by specified name.
     * @see ProjectService#getProjectByName
     *
     * @param name of the required project
     * @return specified project
     */
    @Override
    public Project getProjectByName(String name) {
        return projectRepository.getProjectByName(name);
    }

    /**
     * Updates the specified project.
     * @see ProjectService#updateProject
     *
     * @param projectDTO object which contains updated project information
     */
    @Override
    public void updateProject(ProjectDTO projectDTO) {
        Integer projectId = Integer.parseInt(projectDTO.getId());
        Project project = projectRepository.findOne(projectId);
        project.setName(project.getName());
        project.setStatus(project.getStatus());
        project.setStartDate(LocalDate.parse(projectDTO.getStartDate()));
        project.setEndDate(LocalDate.parse(projectDTO.getEndDate()));
        project.setDescription(projectDTO.getDescription());
        projectRepository.saveAndFlush(project);
        Integer managerId = Integer.parseInt(projectDTO.getManagerId());
        User manager = userRepository.findOne(managerId);
        project.setManager(manager);
        for (String id : projectDTO.getEmployees()) {
            Integer employeeId = Integer.parseInt(id);
            User user = userRepository.findOne(employeeId);
            user.setProject(project);
        }
    }

    /**
     * Returns project manager by specified managed project id.
     * @see ProjectService#getProjectManager
     *
     * @param projectId identifier of the managed project
     * @return specified project manager of the specified project
     */
    public User getProjectManager(Integer projectId) {
        return projectRepository.getProjectManager(projectId);
    }

    /**
     * Returns the total number of employees in specified project.
     * @see ProjectService#getProjectEmployeesCount
     *
     * @param id identifier of the required project
     * @return total number of employees of the specified project
     */
    @Override
    public Long getProjectEmployeesCount(Integer id) {
        return projectRepository.getProjectEmployeesCount(id);
    }

    /**
     * Returns the total number of sprints in specified project.
     * @see ProjectService#getProjectSprintsCount
     *
     * @param id identifier of the required project
     * @return total number of sprints of the specified project
     */
    @Override
    public Long getProjectSprintsCount(Integer id) {
        return projectRepository.getProjectSprintsCount(id);
    }

    /**
     * Check whether there is a project with the specified name.
     * @see ProjectService#checkProjectName
     *
     * @param name of specified project
     * @return result of checking
     */
    @Override
    public boolean checkProjectName(String name) {
        return getProjectByName(name) != null;
    }

    /**
     * Returns all managers in the system.
     * @see ProjectService#getAllProjects
     *
     * @return list of all employees
     */
    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
