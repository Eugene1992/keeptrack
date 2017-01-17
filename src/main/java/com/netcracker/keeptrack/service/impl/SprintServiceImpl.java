package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.SprintStatus;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.SprintRepository;
import com.netcracker.keeptrack.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Service interface that describes methods for Sprint entity business logic.
 *
 * @see Sprint
 */
@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void addSprint(Sprint sprint) {
        sprintRepository.saveAndFlush(sprint);
    }

    @Override
    public void deleteSprint(Integer id) {
        sprintRepository.delete(id);
    }

    @Override
    public Sprint getSprintById(Integer id) {
        return sprintRepository.findOne(id);
    }

    @Override
    public void editSprint(Sprint sprint) {
        sprintRepository.saveAndFlush(sprint);
    }

    @Override
    public void deleteSprintFormProject(Integer id) {
        Sprint sprint = sprintRepository.findOne(id);
        sprint.setProject(null);
        sprintRepository.save(sprint);
    }

    @Override
    public void addSprintToProject(String projectName, String sprintName, String sprintEndDate, String sprintDescription) {
        Sprint sprint = new Sprint();
        Project project = projectRepository.getProjectByName(projectName);
        sprint.setProject(project);
        LocalDate endDate = LocalDate.parse(sprintEndDate);
        sprint.setEndDate(endDate);
        LocalDate startDate = LocalDate.now();
        sprint.setStartDate(startDate);
        sprint.setName(sprintName);
        sprint.setDescription(sprintDescription);
        sprint.setStatus(SprintStatus.CREATED);
        sprintRepository.saveAndFlush(sprint);
    }
}
