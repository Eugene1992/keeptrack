package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.web.dto.ProjectDTO;

import java.util.List;

/**
 * Project interface that describes methods for Project entity business logic.
 *
 * @see Project
 */
public interface ProjectService {

    void addProject(ProjectDTO project);

    void deleteProject(Integer id);

    Project getProjectById(Integer id);

    void editProject(Project project);

    List<Project> getAllProjects();

    User getProjectManager(Integer id);

    Long getProjectEmployeesCount(Integer id);

    Long getProjectSprintsCount(Integer id);

    void deleteEmployeeFormProject(Integer id);
}
