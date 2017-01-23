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

    /**
     * Adds a new project to the system.
     * This function is only available for the administrator.
     *
     * @param projectDTO object which contains information about the new project
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void addProject(ProjectDTO projectDTO);

    /**
     * Deletes the project by specified identifier.
     * This function is only available for the administrator.
     *
     * @param id deleted project identifier
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void deleteProject(Integer id);

    /**
     * Returns the project by specified id.
     * This function is only available for the administrator.
     * The manager and the user only know about the project in which they work.
     *
     * @param id of the required project
     * @return specified project
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    Project getProjectById(Integer id);

    /**
     * Returns the project by specified name.
     * This function is only available for the administrator.
     * The manager and the user only know about the project in which work.
     *
     * @param name of the required project
     * @return specified project
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    Project getProjectByName(String name);

    /**
     * Updates the specified project.
     * This function is only available for the administrator.
     * The manager and the user only know about the project in which work.
     *
     * @param projectDTO object which contains updated project information
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void updateProject(ProjectDTO projectDTO);

    /**
     * Returns all managers in the system.
     * This function is only available for the administrator.
     * The manager and the user only know about the project in which work.
     *
     * @return list of all employees
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<Project> getAllProjects();

    /**
     * Returns project manager by specified managed project id.
     *
     * @param projectId identifier of the managed project
     * @return specified project manager of the specified project
     */
    User getProjectManager(Integer projectId);

    /**
     * Returns the total number of employees in specified project.
     *
     * @param id identifier of the required project
     * @return total number of employees of the specified project
     */
    Long getProjectEmployeesCount(Integer id);

    /**
     * Returns the total number of sprints in specified project.
     *
     * @param id identifier of the required project
     * @return total number of sprints of the specified project
     */
    Long getProjectSprintsCount(Integer id);

    /**
     * Check whether there is a project with the specified name.
     *
     * @param name of specified project
     * @return result of checking
     */
    boolean checkProjectName(String name);
}
