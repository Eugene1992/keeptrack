package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Sprint interface that describes methods for Sprint entity business logic.
 *
 * @see Sprint
 */
public interface SprintService {

    /**
     * Adds a new sprint to the system.
     * This function is only available for the administrator.
     * Project manager can add sprint only to his managed project.
     *
     * @param sprintDTO object which contains information about the new sprint
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void addSprint(SprintDTO sprintDTO);

    /**
     * Deletes the sprint by specified identifier.
     * This function is only available for the administrator.
     *
     * @param id deleted sprint identifier
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void deleteSprint(Integer id);

    /**
     * Returns the sprint by specified id.
     * This function is only available for the administrator.
     * The manager and the user only know about the sprint of the project in which they work.
     *
     * @param id of the required sprint
     * @return specified sprint
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    Sprint getSprintById(Integer id);

    /**
     * Returns the sprint by specified name.
     * This function is only available for the administrator.
     * The manager and the user only know about the sprint of the project in which they work.
     *
     * @param name of the required sprint
     * @return specified sprint
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    Sprint getSprintByName(String name);

    /**
     * Updates the specified sprint.
     * This function is only available for the administrator.
     * The manager and the user only know about the sprint of the project in which they work.
     *
     * @param sprintDTO object which contains updated sprint information
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    void updateSprint(SprintDTO sprintDTO);

    /**
     * Returns all sprints in the system.
     * This function is only available for the administrator.
     * The manager and the user only know about the sprint of the project in which they work.
     *
     * @return list of all sprints
     */
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    List<Sprint> getAllSprints();

    /**
     * Deletes the sprint from current project by specified identifier.
     * This function is only available for the administrator in project profile.
     *
     * @param id deleted sprint identifier
     */
    void deleteSprintFromProject(Integer id);

    // todo: 23.01.2017 refactor for dto input
    /**
     * Adds the sprint to current project by specified identifier.
     * This function is available for the administrator and project manager in project profile.
     *
     */
    void addSprintToProject(String projectName, String sprintName, String sprintEndDate, String sprintDescription);

    /**
     * Check whether there is a sprint with the specified name.
     *
     * @param name of specified sprint
     * @return result of checking
     */
    boolean checkSprintName(String name);
}
