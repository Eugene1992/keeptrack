package com.netcracker.keeptrack.model;

import javax.persistence.*;

/**
 * The smallest piece of a job that serves as a unit of work a project.
 *
 * @see BaseEntity
 * @see TaskStatus
 */
@Entity
public class Task extends BaseEntity {

    /**
     * Task title.
     */
    private String name;

    /**
     * Project manager which creates current task.
     */
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    /**
     * The employee to whom the task is assigned.
     */
    @ManyToOne
    @JoinColumn(name = "assigner_id")
    private User assigner;

    /**
     * Task sprint.
     */
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    /**
     * Estimated time to complete the task.
     */
    private int estimate;

    /**
     * Current status of task execution.
     */
    private TaskStatus status;

    /**
     * Brief description of the task.
     */
    private String description;

    public Task() {
    }

    public Task(String name, User creator, User assigner, Sprint sprint,
                int estimate, TaskStatus status, String description) {
        this.name = name;
        this.creator = creator;
        this.assigner = assigner;
        this.sprint = sprint;
        this.estimate = estimate;
        this.status = status;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
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

        Task task = (Task) o;

        if (estimate != task.estimate) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (creator != null ? !creator.equals(task.creator) : task.creator != null) return false;
        if (assigner != null ? !assigner.equals(task.assigner) : task.assigner != null) return false;
        if (sprint != null ? !sprint.equals(task.sprint) : task.sprint != null) return false;
        if (status != task.status) return false;
        return description != null ? description.equals(task.description) : task.description == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (assigner != null ? assigner.hashCode() : 0);
        result = 31 * result + (sprint != null ? sprint.hashCode() : 0);
        result = 31 * result + estimate;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
