package com.netcracker.keeptrack.model;

/**
 * The smallest piece of a job that serves as a unit of work a project.
 *
 * @see BaseEntity
 * @see TaskStatus
 */
public class Task extends BaseEntity {

    /**
     * Task title.
     */
    private String name;

    /**
     * The employee who created the task.
     */
    private Object creator;

    /**
     * The employee to whom the task is assigned.
     */
    private Object assigner;

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

    public Task(int id, String name, Object creator, Object assigner, int estimate, TaskStatus status, String description) {
        super(id);
        this.name = name;
        this.creator = creator;
        this.assigner = assigner;
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

    public Object getCreator() {
        return creator;
    }

    public void setCreator(Object creator) {
        this.creator = creator;
    }

    public Object getAssigner() {
        return assigner;
    }

    public void setAssigner(Object assigner) {
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
}
