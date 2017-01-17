package com.netcracker.keeptrack.web.dto;

import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Project entity Data Transfer Object.
 */
public class ProjectDTO {

    /**
     * Project title.
     */
    @NotNull(message = "Please select project name")
    @Size(min = 3, max = 10, message = "Name length must be in 3 to 10 chars")
    private String name;

    /**
     * Project manager credentials.
     */
    @NotNull(message = "Please select project manager")
    private String manager;

    /**
     * Project title.
     */
    @Size(min = 10, max = 100, message = "Description length must be in 10 to 100 chars")
    private String description;

    /**
     * Project employees credentials.
     */
    @NotNull
    private Set<String> employees;

    public ProjectDTO() {
    }

    public ProjectDTO(String name, String manager, String description, Set<String> employees) {
        this.name = name;
        this.manager = manager;
        this.description = description;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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
}
