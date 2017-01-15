package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;

/**
 * Project interface that describes methods for Project entity business logic.
 *
 * @see Project
 */
public interface ProjectService {

    void addProject(Project project);

    void deleteProject(Integer id);

    Project getProjectById(Integer id);

    void editProject(Project project);

    User getProjectManager(Integer id);

    Long getProjectEmployeesCount(Integer id);

    Long getProjectSprintsCount(Integer id);
}
