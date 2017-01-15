package com.netcracker.keeptrack.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Period of time during which specific work has to be completed and made ready for review.
 * Sprints managed by the project Manager. Consists of tasks. Also Sprint is part of a project.
 *
 * @see BaseEntity
 * @see Task
 */
@Entity
public class Sprint extends BaseEntity {

    /**
     * Sprint name.
     */
    private String name;

    /**
     * Sprint project.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * Sprint tasks.
     */
    @OneToMany(mappedBy = "sprint")
    private Set<Task> tasks;

    /**
     * Current status of sprint execution.
     */
    @Enumerated(value = EnumType.STRING)
    private SprintStatus status;

    /**
     * Brief description of the sprint.
     */
    private String description;

    public Sprint() {
    }

    public Sprint(String name, Project project, Set<Task> tasks, SprintStatus status, String description) {
        this.name = name;
        this.project = project;
        this.tasks = tasks;
        this.status = status;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public SprintStatus getStatus() {
        return status;
    }

    public void setStatus(SprintStatus status) {
        this.status = status;
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

        Sprint sprint = (Sprint) o;

        if (name != null ? !name.equals(sprint.name) : sprint.name != null) return false;
        if (status != sprint.status) return false;
        return description != null ? description.equals(sprint.description) : sprint.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}