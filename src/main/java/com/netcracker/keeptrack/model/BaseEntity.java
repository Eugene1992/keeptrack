package com.netcracker.keeptrack.model;

/**
 * Base class for the entities stored in the database, which should contain the id.
 */
public class BaseEntity {

    /**
     * Unique identifier for storage in data base.
     */
    protected int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
