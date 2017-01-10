package com.netcracker.keeptrack.model;

import java.util.List;

/**
 * Period of time during which specific work has to be completed and made ready for review.
 * Sprints managed by the project Manager. Consists of tasks. Also Sprint is part of a project.
 *
 * @see BaseEntity
 * @see Task
 */
public class Sprint extends BaseEntity {

    /**
     * The project, part of which is the current sprint.
     */
    private Object project;

    /**
     * Current status of sprint execution.
     */
    private SprintStatus status;

    /**
     * Tasks included in the sprint.
     */
    private List<Task> tasks;

    /**
     * Brief description of the sprint.
     */
    private String description;

    public Sprint(int id, Object project, SprintStatus status, List<Task> tasks, String description) {
        super(id);
        this.project = project;
        this.status = status;
        this.tasks = tasks;
        this.description = description;
    }

    public Object getProject() {
        return project;
    }

    public void setProject(Object project) {
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
}