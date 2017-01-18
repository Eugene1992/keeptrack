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
 * @see Project
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addProject(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setStartDate(LocalDate.now());
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

    @Override
    public void deleteProject(Integer id) {
        projectRepository.delete(id);
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectRepository.findOne(id);
    }

    @Override
    public Project getProjectByName(String name) {
        return projectRepository.getProjectByName(name);
    }

    @Override
    public void updateProject(ProjectDTO projectDTO) {
        Integer projectId = Integer.parseInt(projectDTO.getId());
        Project project = projectRepository.findOne(projectId);
        Integer managerId = Integer.parseInt(projectDTO.getManagerId());
        User manager = userRepository.findOne(managerId);
        project.setManager(manager);
        for (String id : projectDTO.getEmployees()) {
            Integer employeeId = Integer.parseInt(id);
            User user = userRepository.findOne(employeeId);
            user.setProject(project);
        }
        project.setName(project.getName());
        project.setStatus(project.getStatus());
        project.setStartDate(LocalDate.parse(projectDTO.getStartDate()));
        project.setEndDate(LocalDate.parse(projectDTO.getEndDate()));
        project.setDescription(projectDTO.getDescription());
        projectRepository.saveAndFlush(project);
    }

    public User getProjectManager(Integer id) {
        return projectRepository.getProjectManager(id);
    }

    @Override
    public Long getProjectEmployeesCount(Integer id) {
        return projectRepository.getProjectEmployeesCount(id);
    }

    @Override
    public Long getProjectSprintsCount(Integer id) {
        return projectRepository.getProjectSprintsCount(id);
    }

    @Override
    public boolean checkProjectName(String name) {
        return getProjectByName(name) != null;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
