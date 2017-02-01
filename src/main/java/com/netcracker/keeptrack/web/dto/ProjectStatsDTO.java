package com.netcracker.keeptrack.web.dto;

/**
 * Project statistic Data Transfer Object.
 */
public class ProjectStatsDTO {

    /**
     * Total project assigned tasks number.
     */
    private Long assignedTasksNum;

    /**
     * Total project closed tasks number.
     */
    private Long closedTasksNum;

    /**
     * Total project rejected tasks number.
     */
    private Long rejectedTasksNum;

    /**
     * Total project in progress tasks number.
     */
    private Long inProgressTasksNum;

    /**
     * Total project in progress sprints number.
     */
    private Long inProgressSprintsNum;

    /**
     * Total project closed sprints number.
     */
    private Long closedSprintsNum;

    /**
     * Project used time in percent.
     */
    private Long usedTimePercent;

    public ProjectStatsDTO() {
    }

    public Long getAssignedTasksNum() {
        return assignedTasksNum;
    }

    public void setAssignedTasksNum(Long assignedTasksNum) {
        this.assignedTasksNum = assignedTasksNum;
    }

    public Long getClosedTasksNum() {
        return closedTasksNum;
    }

    public void setClosedTasksNum(Long closedTasksNum) {
        this.closedTasksNum = closedTasksNum;
    }

    public Long getRejectedTasksNum() {
        return rejectedTasksNum;
    }

    public void setRejectedTasksNum(Long rejectedTasksNum) {
        this.rejectedTasksNum = rejectedTasksNum;
    }

    public Long getInProgressSprintsNum() {
        return inProgressSprintsNum;
    }

    public void setInProgressSprintsNum(Long inProgressSprintsNum) {
        this.inProgressSprintsNum = inProgressSprintsNum;
    }

    public Long getClosedSprintsNum() {
        return closedSprintsNum;
    }

    public void setClosedSprintsNum(Long closedSprintsNum) {
        this.closedSprintsNum = closedSprintsNum;
    }

    public Long getUsedTimePercent() {
        return usedTimePercent;
    }

    public void setUsedTimePercent(Long usedTimePercent) {
        this.usedTimePercent = usedTimePercent;
    }

    public Long getInProgressTasksNum() {
        return inProgressTasksNum;
    }

    public void setInProgressTasksNum(Long inProgressTasksNum) {
        this.inProgressTasksNum = inProgressTasksNum;
    }
}
