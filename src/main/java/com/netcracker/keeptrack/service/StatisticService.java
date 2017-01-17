package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface that describes methods for statistic and dashboard functional.
 */
@Service
public interface StatisticService {

    Long getTotalCustomersCount();

    Long getTotalProjectsCount();

    Long getTotalTasksCount();

    Long getTotalEmployeesCount();

    List<Task> getLatestTasks(Integer limit);
}
