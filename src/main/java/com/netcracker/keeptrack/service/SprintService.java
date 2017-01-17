package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Sprint;

/**
 * Sprint interface that describes methods for Sprint entity business logic.
 *
 * @see Sprint
 */
public interface SprintService {

    void addSprint(Sprint sprint);

    void deleteSprint(Integer id);

    Sprint getSprintById(Integer id);

    void editSprint(Sprint sprint);

    void deleteSprintFormProject(Integer id);

    void addSprintToProject(String projectName, String sprintName, String sprintEndDate, String sprintDescription);
}
