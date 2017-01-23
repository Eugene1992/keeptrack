package com.netcracker.keeptrack.web.dto;

import com.netcracker.keeptrack.model.Sprint;

/**
 * Sprint entity Data Transfer Object.
 */
public class SprintDTO {

    /**
     * Sprint id.
     */
    private String id;

    /**
     * Sprint name.
     */
    private String name;

    /**
     * Sprint start date.
     */
    private String startDate;

    /**
     * Sprint end date.
     */
    private String endDate;

    /**
     * Sprint end date.
     */
    private String status;

    /**
     * Sprint title.
     */
    private String description;

    /**
     * Project employees credentials.
     */
    private String projectId;

    public SprintDTO() {
    }

    public SprintDTO(Sprint sprint) {
        this.id = String.valueOf(sprint.getId());
        this.name = sprint.getName();
        this.startDate = sprint.getStartDate().toString();
        this.endDate = sprint.getEndDate().toString();
        this.status = sprint.getStatus().name();
        this.description = sprint.getDescription();
        this.projectId = String.valueOf(sprint.getProject().getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
