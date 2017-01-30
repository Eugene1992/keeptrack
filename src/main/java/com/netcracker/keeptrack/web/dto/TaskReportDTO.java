package com.netcracker.keeptrack.web.dto;

import com.netcracker.keeptrack.model.Task;

/**
 * Task report Data Transfer Object.
 */
public class TaskReportDTO {

    /**
     * Task entity instance.
     */
    private Task task;

    /**
     * Task duration.
     */
    private Integer duration;

    /**
     * Task human hours.
     */
    private Integer humanHours;

    /**
     * Task deviation.
     */
    private Integer deviation;

    public TaskReportDTO(Task task, Integer duration, Integer humanHours, Integer deviation) {
        this.task = task;
        this.duration = duration;
        this.humanHours = humanHours;
        this.deviation = deviation;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getHumanHours() {
        return humanHours;
    }

    public void setHumanHours(Integer humanHours) {
        this.humanHours = humanHours;
    }

    public Integer getDeviation() {
        return deviation;
    }

    public void setDeviation(Integer deviation) {
        this.deviation = deviation;
    }
}
