package com.netcracker.keeptrack.web.dto;

import com.netcracker.keeptrack.model.Task;

/**
 * Task entity Data Transfer Object.
 */
public class TaskDTO {

    /**
     * Task id.
     */
    protected String id;

    /**
     * Task name.
     */
    protected String name;

    /**
     * Task start date.
     */
    protected String startDate;

    /**
     * Task end date.
     */
    private String endDate;

    /**
     * Task end date.
     */
    protected String status;

    /**
     * Task sprint id.
     */
    protected String sprintId;

    /**
     * Task creator id.
     */
    protected String creatorId;

    /**
     * Task assigner id.
     */
    protected String assignerId;

    /**
     * Task description.
     */
    protected String description;

    /**
     * Task estimate.
     */
    protected String estimate;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.id = String.valueOf(task.getId());
        this.name = task.getName();
        this.startDate = task.getStartDate().toString();
        this.endDate = task.getEndDate().toString();
        this.status = task.getStatus().name();
        this.sprintId = String.valueOf(task.getSprint().getId());
        this.creatorId = String.valueOf(task.getCreator().getId());
        this.assignerId = String.valueOf(task.getAssigner().getId());
        this.description = task.getDescription();
        this.estimate = String.valueOf(task.getEstimate());
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
