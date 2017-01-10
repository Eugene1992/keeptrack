package com.netcracker.keeptrack.model;

/**
 * The statuses of the stages of sprint execution.
 * Next sprint can be opened only after the closing of the previous sprint.
 *
 * @see Sprint
 */
public enum SprintStatus {

    /**
     * The sprint is created but not started because there is not a closed previous sprint.
     */
    CREATED("Created"),

    /**
     * Sprint is in the execution stage.
     */
    IN_PROGRESS("In progress"),

    /**
     * The sprint is closed.
     */
    CLOSED("Closed");

    /**
     * Sprint status title.
     */
    private String name;

    SprintStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
