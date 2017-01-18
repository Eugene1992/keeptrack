package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.web.dto.SprintDTO;

import java.util.List;

/**
 * Sprint interface that describes methods for Sprint entity business logic.
 *
 * @see Sprint
 */
public interface SprintService {

    void addSprint(SprintDTO sprintDTO);

    void deleteSprint(Integer id);

    Sprint getSprintById(Integer id);

    Sprint getSprintByName(String name);

    void updateSprint(SprintDTO sprintDTO);

    List<Sprint> getAllSprints();

    void deleteSprintFormProject(Integer id);

    void addSprintToProject(String projectName, String sprintName, String sprintEndDate, String sprintDescription);

    boolean checkSprintName(String name);
}
