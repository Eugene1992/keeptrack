package com.netcracker.keeptrack.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @OneToMany(mappedBy = "project")
    private List<User> users;

    /**
     * The sprints of the project.
     */
    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints;

    /**
     * Current status of sprint execution.
     */
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus status;

    /**
     * Date of start of the project.
     */
    private LocalDate startDate;

    /**
     * Date of end of the project.
     */
    private LocalDate endDate;

    public Project() {
    }

    public Project(String name, User manager, List<User> users, List<Sprint> sprints,
                   ProjectStatus status, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.manager = manager;
        this.users = users;
        this.sprints = sprints;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (manager != null ? !manager.equals(project.manager) : project.manager != null) return false;
        if (users != null ? !users.equals(project.users) : project.users != null) return false;
        if (sprints != null ? !sprints.equals(project.sprints) : project.sprints != null) return false;
        if (status != project.status) return false;
        return startDate != null ? startDate.equals(project.startDate) : project.startDate == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        result = 31 * result + (sprints != null ? sprints.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }
}
