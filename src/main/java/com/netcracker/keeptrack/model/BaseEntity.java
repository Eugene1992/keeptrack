package com.netcracker.keeptrack.model;

/**
 * Base class for the entities stored in the database, which should contain the id.
 */
public class BaseEntity {

    /**
     * Unique identifier for storage in data base.
     */
    protected Integer id;

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
