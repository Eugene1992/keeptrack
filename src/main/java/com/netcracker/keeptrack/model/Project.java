package com.netcracker.keeptrack.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The project is a collection of tasks or activities related to the achievement of the planned goals.
 * Consists of sprints.
 *
 * @see User
 * @see Task
 * @see Sprint
 */
@Entity
public class Project extends BaseEntity {

    /**
     * Project title.
     */
    private String name;

    /**
     * The manager of the current project.
     */
    @OneToOne(mappedBy = "managedProject")
    private User manager;

    /**
     * Employees who works on a current project.
     */
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<User> users;

    /**
     * The sprints of the project.
     */
    @OneToMany(mappedBy = "project")
    private Set<Sprint> sprints;

    /**
     * Current status of sprint execution.
     */
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;

    /**
     * Date of start of the project.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    /**
     * Date of end of the project.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    /**
     * Date of end of the project.
     */
    private String description;

    public Project() {
    }

    public Project(String name, User manager, Set<User> users, Set<Sprint> sprints,
                   ProjectStatus status, LocalDate startDate, LocalDate endDate, String description) {
        this.name = name;
        this.manager = manager;
        this.users = users;
        this.sprints = sprints;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (status != project.status) return false;
        return startDate != null ? startDate.equals(project.startDate) : project.startDate == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }
}
