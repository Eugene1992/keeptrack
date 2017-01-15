package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void addProject(Project project) {
        projectRepository.saveAndFlush(project);
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
}
