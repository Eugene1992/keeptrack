package com.netcracker.keeptrack.web.dto;

import com.netcracker.keeptrack.model.Project;

import java.util.List;

/**
 * Project report Data Transfer Object.
 */
public class ProjectReportDTO {

    /**
     * Project entity instance.
     */
    private Project project;

    /**
     * Total number of the non delayed tasks.
     */
    private long numOfNonDelayedTasks;

    /**
     * Total number of the delayed tasks.
     */
    private long numOfDelayedTasks;

    /**
     * Total number of the non delayed sprints.
     */
    private long numOfNonDelayedSprints;

    /**
     * Total number of the delayed sprints.
     */
    private long numOfDelayedSprints;

    /**
     * Projects DTO tasks with additional info.
     */
    private List<TaskReportDTO> dtoTasks;

    /**
     * Projects DTO sprints with additional info.
     */
    private List<SprintReportDTO> dtoSprints;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getNumOfNonDelayedTasks() {
        return numOfNonDelayedTasks;
    }

    public void setNumOfNonDelayedTasks(long numOfNonDelayedTasks) {
        this.numOfNonDelayedTasks = numOfNonDelayedTasks;
    }

    public long getNumOfDelayedTasks() {
        return numOfDelayedTasks;
    }

    public void setNumOfDelayedTasks(long numOfDelayedTasks) {
        this.numOfDelayedTasks = numOfDelayedTasks;
    }

    public long getNumOfNonDelayedSprints() {
        return numOfNonDelayedSprints;
    }

    public void setNumOfNonDelayedSprints(long numOfNonDelayedSprints) {
        this.numOfNonDelayedSprints = numOfNonDelayedSprints;
    }

    public long getNumOfDelayedSprints() {
        return numOfDelayedSprints;
    }

    public void setNumOfDelayedSprints(long numOfDelayedSprints) {
        this.numOfDelayedSprints = numOfDelayedSprints;
    }

    public List<TaskReportDTO> getDtoTasks() {
        return dtoTasks;
    }

    public void setDtoTasks(List<TaskReportDTO> dtoTasks) {
        this.dtoTasks = dtoTasks;
    }

    public List<SprintReportDTO> getDtoSprints() {
        return dtoSprints;
    }

    public void setDtoSprints(List<SprintReportDTO> dtoSprints) {
        this.dtoSprints = dtoSprints;
    }
}
