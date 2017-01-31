package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.web.dto.ProjectStatsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface that describes methods for statistic and dashboard functional.
 */
@Service
public interface StatisticService {

    /**
     * Returns the total number of customers in the system.
     *
     * @return total number of customers
     */
    Long getTotalCustomersCount();

    /**
     * Returns the total number of projects in the system.
     *
     * @return total number of projects
     */
    Long getTotalProjectsCount();

    /**
     * Returns the total number of tasks in the system.
     *
     * @return total number of tasks
     */
    Long getTotalTasksCount();

    /**
     * Returns the total number of employees in the system.
     *
     * @return total number of employees
     */
    Long getTotalEmployeesCount();

    /**
     * Returns the latest by date specified number of tasks.
     *
     * @param limit number of required tasks
     * @return list of latest tasks
     */
    List<Task> getLatestTasks(Integer limit);

    /**
     * Returns project statistic.
     *
     * @return project statistic data
     */
    ProjectStatsDTO getProjectStatistic(Project project);
}
