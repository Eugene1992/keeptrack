package com.netcracker.keeptrack.model;

/**
 * The statuses of the stages of task execution.
 *
 * @see Task
 */
public enum TaskStatus {

    /**
     * The task is created but not open. Perhaps because there is not a closed dependent task.
     */
    CREATED("Created"),

    /**
     * The task is opened by the Project Manager, but the assigner had not yet accepted her.
     */
    OPEN("Open"),

    /**
     * The assignee has accepted the task and it is in execution progress.
     */
    IN_PROGRESS("In progress"),

    /**
     * The task is solved, but requires review or clarification.
     */
    RESOLVED("Resolved"),

    /**
     * The task is solved and closed.
     */
    CLOSED("Closed");

    /**
     * Task status title.
     */
    private String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
