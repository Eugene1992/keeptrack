package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.SprintStatus;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.SprintRepository;
import com.netcracker.keeptrack.service.SprintService;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of {@link SprintService} interface that provides methods for Sprint
 * entity business logic.
 *
 * @see SprintService
 * @see Sprint
 */
@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Adds a new sprint to the system.
     * A new sprint is created on the basis of the data parsed by {@link SprintDTO} object.
     * Data is previously validated by SprintValidator.
     * @see SprintService#addSprint
     *
     * @param sprintDTO object which contains information about the new sprint
     */
    @Override
    public void addSprint(SprintDTO sprintDTO) {
        Sprint sprint = new Sprint();
        Integer projectId = Integer.parseInt(sprintDTO.getProjectId());
        Project project = projectRepository.findOne(projectId);
        LocalDate startDate = LocalDate.parse(sprintDTO.getStartDate());
        LocalDate endDate = LocalDate.parse(sprintDTO.getEndDate());
        SprintStatus status = SprintStatus.valueOf(sprintDTO.getStatus());
        sprint.setProject(project);
        sprint.setName(sprintDTO.getName());
        sprint.setStartDate(startDate);
        sprint.setEndDate(endDate);
        sprint.setDescription(sprintDTO.getDescription());
        sprint.setStatus(status);
        sprintRepository.saveAndFlush(sprint);
    }

    /**
     * Deletes the sprint by specified identifier.
     * @see SprintService#deleteSprint
     *
     * @param id deleted sprint identifier
     */
    @Override
    public void deleteSprint(Integer id) {
        sprintRepository.delete(id);
    }

    /**
     * Returns the sprint by specified id.
     * @see SprintService#getSprintById
     *
     * @param id of the required sprint
     * @return specified sprint
     */
    @Override
    public Sprint getSprintById(Integer id) {
        return sprintRepository.findOne(id);
    }

    /**
     * Returns the sprint by specified name.
     * @see SprintService#getSprintByName
     *
     * @param name of the required sprint
     * @return specified sprint
     */
    @Override
    public Sprint getSprintByName(String name) {
        return sprintRepository.getSprintByName(name);
    }

    /**
     * Updates the specified sprint.
     * @see SprintService#updateSprint
     *
     * @param sprintDTO object which contains updated sprint information
     */
    @Override
    public void updateSprint(SprintDTO sprintDTO) {
        Integer sprintId = Integer.parseInt(sprintDTO.getId());
        Integer sprintProjectId = Integer.parseInt(sprintDTO.getProjectId());
        Sprint sprint = sprintRepository.findOne(sprintId);
        Project sprintProject = projectRepository.findOne(sprintProjectId);
        sprint.setProject(sprintProject);
        sprint.setName(sprintDTO.getName());
        sprint.setStatus(SprintStatus.valueOf(sprintDTO.getStatus()));
        sprint.setDescription(sprintDTO.getDescription());
        sprint.setStartDate(LocalDate.parse(sprintDTO.getStartDate()));
        sprint.setEndDate(LocalDate.parse(sprintDTO.getEndDate()));
        sprintRepository.saveAndFlush(sprint);
    }

    /**
     * Returns all sprints in the system.
     * @see SprintService#getAllSprints
     *
     * @return list of all sprints
     */
    @Override
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    /**
     * Deletes the sprint from current project by specified identifier.
     * @see SprintService#deleteSprintFromProject
     *
     * @param id deleted sprint identifier
     */
    @Override
    public void deleteSprintFromProject(Integer id) {
        Sprint sprint = sprintRepository.findOne(id);
        sprint.setProject(null);
        sprintRepository.save(sprint);
    }

    /**
     * Adds the sprint to current project by specified identifier.
     *
     * @see SprintService#addSprintToProject
     */
    @Override
    public void addSprintToProject(SprintDTO sprintDTO) {
        Sprint sprint = new Sprint();
        Project project = projectRepository.findOne(Integer.valueOf(sprintDTO.getProjectId()));
        sprint.setProject(project);
        LocalDate endDate = LocalDate.parse(sprintDTO.getEndDate());
        sprint.setEndDate(endDate);
        LocalDate startDate = LocalDate.parse(sprintDTO.getStartDate());
        sprint.setStartDate(startDate);
        sprint.setName(sprintDTO.getName());
        sprint.setDescription(sprintDTO.getDescription());
        sprint.setStatus(SprintStatus.CREATED);
        sprintRepository.saveAndFlush(sprint);
    }

    /**
     * Check whether there is a sprint with the specified name.
     * @see SprintService#checkSprintName
     *
     * @param name of specified sprint
     * @return result of checking
     */
    @Override
    public boolean checkSprintName(String name) {
        return sprintRepository.getSprintByName(name) != null;
    }

    /**
     * Returns latest project sprint by date.
     *
     * @param projectId specified project
     * @return latest project sprint
     */
    @Override
    public Sprint getProjectLatestSprint(Integer projectId) {
        List<Sprint> projectSprints = sprintRepository.getProjectSprints(projectId);
        return projectSprints.stream()
                .max(Comparator.comparing(Sprint::getEndDate))
                .get();
    }
}
