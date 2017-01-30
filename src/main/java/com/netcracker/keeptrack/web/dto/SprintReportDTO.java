package com.netcracker.keeptrack.web.dto;

import com.netcracker.keeptrack.model.Sprint;

/**
 * Sprint report Data Transfer Object.
 */
public class SprintReportDTO {

    /**
     * Sprint entity instance.
     */
    private Sprint sprint;

    /**
     * Sprint duration.
     */
    private Integer duration;

    /**
     * Sprint human hours.
     */
    private Integer humanHours;

    /**
     * Sprint deviation.
     */
    private Integer deviation;

    public SprintReportDTO(Sprint sprint, Integer duration, Integer humanHours, Integer deviation) {
        this.sprint = sprint;
        this.duration = duration;
        this.humanHours = humanHours;
        this.deviation = deviation;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
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
