package com.netcracker.keeptrack.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    private User creator;

    /**
     * The employee to whom the task is assigned.
     */
    @ManyToOne(cascade = CascadeType.ALL)
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
     * Date of start of the task.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    /**
     * Date of end of the task.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    /**
     * Current status of task execution.
     */
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    /**
     * Brief description of the task.
     */
    private String description;

    public Task() {
    }

    public Task(String name, User creator, User assigner, Sprint sprint, int estimate,
                LocalDate startDate, LocalDate endDate, TaskStatus status, String description) {
        this.name = name;
        this.creator = creator;
        this.assigner = assigner;
        this.sprint = sprint;
        this.estimate = estimate;
        this.startDate = startDate;
        this.endDate = endDate;
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

        Task task = (Task) o;

        if (estimate != task.estimate) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (status != task.status) return false;
        return description != null ? description.equals(task.description) : task.description == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + estimate;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
