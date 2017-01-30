package com.netcracker.keeptrack.web.dto;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.User;

import java.util.List;

/**
 * Employee report Data Transfer Object.
 */
public class EmployeeReportDTO {

    /**
     * User entity instance.
     */
    private User reportedUser;

    /**
     * Filtered (by dates interval) projects in which employee participated.
     */
    private List<Project> projects;

    /**
     * Filtered (by dates interval) sprints in which employee participated.
     */
    private List<Sprint> sprints;

    /**
     * Filtered (by dates interval) DTO tasks in which employee participated.
     */
    private List<TaskReportDTO> dtoTasks;

    /**
     * Total number of the delayed tasks.
     */
    private int numOfDelayedTasks;

    /**
     * Total number of the non delayed tasks.
     */
    private int numOfNonDelayedTasks;

    public User getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(User reportedUser) {
        this.reportedUser = reportedUser;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public List<TaskReportDTO> getDtoTasks() {
        return dtoTasks;
    }

    public void setDtoTasks(List<TaskReportDTO> dtoTasks) {
        this.dtoTasks = dtoTasks;
    }

    public int getNumOfDelayedTasks() {
        return numOfDelayedTasks;
    }

    public void setNumOfDelayedTasks(int numOfDelayedTasks) {
        this.numOfDelayedTasks = numOfDelayedTasks;
    }

    public int getNumOfNonDelayedTasks() {
        return numOfNonDelayedTasks;
    }

    public void setNumOfNonDelayedTasks(int numOfNonDelayedTasks) {
        this.numOfNonDelayedTasks = numOfNonDelayedTasks;
    }
}
