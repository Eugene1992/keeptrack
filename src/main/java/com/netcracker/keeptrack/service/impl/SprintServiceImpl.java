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
import java.util.List;

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

    @Override
    public void deleteSprint(Integer id) {
        sprintRepository.delete(id);
    }

    @Override
    public Sprint getSprintById(Integer id) {
        return sprintRepository.findOne(id);
    }

    @Override
    public Sprint getSprintByName(String name) {
        return sprintRepository.getSprintByName(name);
    }

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

    @Override
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
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

    @Override
    public boolean checkSprintName(String name) {
        return sprintRepository.getSprintByName(name) != null;
    }
}
