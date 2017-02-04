package com.netcracker.keeptrack.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Task estimate change request.
 * The user can make a request to Project Manager to change the estimate time of his task.
 */
@Entity
public class TaskEstimateRequest extends Request {

    /**
     * Selected task for request.
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Requested estimate.
     */
    private int reqEstimate;

    public TaskEstimateRequest(){
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getReqEstimate() {
        return reqEstimate;
    }

    public void setReqEstimate(int reqEstimate) {
        this.reqEstimate = reqEstimate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TaskEstimateRequest that = (TaskEstimateRequest) o;

        if (reqEstimate != that.reqEstimate) return false;
        return task != null ? task.equals(that.task) : that.task == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + reqEstimate;
        return result;
    }
}
