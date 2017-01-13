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
     * The employee who created the task.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private ProjectManager creator;

    /**
     * The employee to whom the task is assigned.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employee assigner;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Sprint sprint;

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectManager getCreator() {
        return creator;
    }

    public void setCreator(ProjectManager creator) {
        this.creator = creator;
    }

    public Employee getAssigner() {
        return assigner;
    }

    public void setAssigner(Employee assigner) {
        this.assigner = assigner;
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

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
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
        if (status != task.status) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        return sprint != null ? sprint.equals(task.sprint) : task.sprint == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (assigner != null ? assigner.hashCode() : 0);
        result = 31 * result + estimate;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (sprint != null ? sprint.hashCode() : 0);
        return result;
    }
}
