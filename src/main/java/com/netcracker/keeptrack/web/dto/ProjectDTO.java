package com.netcracker.keeptrack.web.dto;

import java.util.Set;

/**
 * Project entity Data Transfer Object.
 */
public class ProjectDTO {

    /**
     * Project id.
     */
    private String id;

    /**
     * Project title.
     */
    private String name;

    /**
     * Project managerId credentials.
     */
    private String managerId;

    /**
     * Project status.
     */
    private String status;

    /**
     * Project title.
     */
    private String startDate;

    /**
     * Project title.
     */
    private String endDate;

    /**
     * Project title.
     */
    private String description;

    /**
     * Project employees credentials.
     */
    private Set<String> employees;

    public ProjectDTO() {
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

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<String> employees) {
        this.employees = employees;
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
}
