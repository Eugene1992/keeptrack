package com.netcracker.keeptrack.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for the entities stored in the database, which should contain the id.
 */
@MappedSuperclass
class BaseEntity {

    /**
     * Unique identifier for storage in data base.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    BaseEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
