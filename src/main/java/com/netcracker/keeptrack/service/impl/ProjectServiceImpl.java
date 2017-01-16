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
        Integer managerId = Integer.parseInt(dto.getManager());
        User manager = userRepository.findOne(managerId);
        manager.setManagedProject(project);
        userRepository.save(manager);
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
    public void editProject(Project project) {
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
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
