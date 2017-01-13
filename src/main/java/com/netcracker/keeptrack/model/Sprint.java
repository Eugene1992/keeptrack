package com.netcracker.keeptrack.model;

import javax.persistence.*;
import java.util.List;

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
     * The project, part of which is the current sprint.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Project project;

    /**
     * Current status of sprint execution.
     */
    private SprintStatus status;

    /**
     * Tasks included in the sprint.
     */
    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;

    /**
     * Brief description of the sprint.
     */
    private String description;

    /**
     * Project Manager created currnt Sprint.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private ProjectManager creator;

    public Sprint() {
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public SprintStatus getStatus() {
        return status;
    }

    public void setStatus(SprintStatus status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectManager getCreator() {
        return creator;
    }

    public void setCreator(ProjectManager creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprint sprint = (Sprint) o;

        if (project != null ? !project.equals(sprint.project) : sprint.project != null) return false;
        if (status != sprint.status) return false;
        if (tasks != null ? !tasks.equals(sprint.tasks) : sprint.tasks != null) return false;
        if (description != null ? !description.equals(sprint.description) : sprint.description != null) return false;
        return creator != null ? creator.equals(sprint.creator) : sprint.creator == null;
    }

    @Override
    public int hashCode() {
        int result = project != null ? project.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }
}