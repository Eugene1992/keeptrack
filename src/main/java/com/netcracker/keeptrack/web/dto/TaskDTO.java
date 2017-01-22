package com.netcracker.keeptrack.web.dto;

/**
 * Task entity Data Transfer Object.
 */
public class TaskDTO {

    /**
     * Task id.
     */
    private String id;

    /**
     * Task name.
     */
    private String name;

    /**
     * Task start date.
     */
    private String startDate;

    /**
     * Task end date.
     */
    private String endDate;

    /**
     * Task end date.
     */
    private String status;

    /**
     * Task sprint id.
     */
    private String sprintId;

    /**
     * Task creator id.
     */
    private String creatorId;

    /**
     * Task assigner id.
     */
    private String assignerId;

    /**
     * Task description.
     */
    private String description;

    /**
     * Task estimate.
     */
    private String estimate;

    public TaskDTO() {
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(String assignerId) {
        this.assignerId = assignerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }
}
