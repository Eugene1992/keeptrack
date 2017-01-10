package com.netcracker.keeptrack.model;

/**
 * The statuses of the stages of project execution.
 *
 * @see Sprint
 */
public enum ProjectStatus {

    /**
     * The project is created but not started for some reason
     * (tasks are prepared only, not enough available employees etc).
     */
    CREATED("Created"),

    /**
     * The project has started and is at the stage of execution.
     */
    IN_PROGRESS("In progress"),

    /**
     * The sprint is closed.
     */
    CLOSED("Closed");

    /**
     * The project is completed and closed.
     */
    private String name;

    ProjectStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
