package com.netcracker.keeptrack.model;

import java.time.LocalDate;
import java.util.List;

/**
 * The project is a collection of tasks or activities related to the achievement of the planned goals.
 * Consists of sprints.
 *
 * @see Employee
 * @see Task
 * @see Sprint
 */
public class Project {

    /**
     * Project title.
     */
    private String name;

    /**
     * The manager of the current project.
     */
    private Employee manager;

    /**
     * Current status of sprint execution.
     */
    private ProjectStatus status;

    /**
     * Date of start of the project.
     */
    private LocalDate startDate;

    /**
     * The sprints of the project.
     */
    private List<Sprint> sprints;

    /**
     * Employees who work on a current project.
     */
    private List<Employee> employees;

    public Project(String name, Employee manager, ProjectStatus status, LocalDate startDate, List<Sprint> sprints, List<Employee> employees) {
        this.name = name;
        this.manager = manager;
        this.status = status;
        this.startDate = startDate;
        this.sprints = sprints;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
