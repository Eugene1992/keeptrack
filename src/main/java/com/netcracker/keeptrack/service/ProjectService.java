package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.web.dto.ProjectDTO;
import org.springframework.security.access.prepost.PreAuthorize;

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

    Project getProjectByName(String name);

    void updateProject(ProjectDTO projectDTO);

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<Project> getAllProjects();

    User getProjectManager(Integer projectId);

    Long getProjectEmployeesCount(Integer id);

    Long getProjectSprintsCount(Integer id);

    boolean checkProjectName(String name);
}
