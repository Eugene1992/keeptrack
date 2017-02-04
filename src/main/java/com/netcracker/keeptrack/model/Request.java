package com.netcracker.keeptrack.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Base class, that describes user request.
 */
@Entity
public class Request extends BaseEntity {

    /**
     * Request tittle.
     */
    protected String tittle;

    /**
     * Request creator.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "creator_id")
    protected User creator;

    /**
     * Request assigner.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "assigner_id")
    protected User assigner;

    /**
     * Request status.
     */
    @Enumerated(value = EnumType.STRING)
    protected RequestStatus status;

    /**
     * Request description.
     */
    protected String description;

    public Request() {
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
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
        if (!super.equals(o)) return false;

        Request request = (Request) o;

        if (creator != null ? !creator.equals(request.creator) : request.creator != null) return false;
        if (assigner != null ? !assigner.equals(request.assigner) : request.assigner != null) return false;
        if (status != request.status) return false;
        return description != null ? description.equals(request.description) : request.description == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (assigner != null ? assigner.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
