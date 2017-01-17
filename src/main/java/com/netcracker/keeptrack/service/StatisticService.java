package com.netcracker.keeptrack.service;

import org.springframework.stereotype.Service;

/**
 * Service interface that describes methods for statistic and dashboard functional.
 */
@Service
public interface StatisticService {

    Long getTotalCustomersCount();

    Long getTotalProjectsCount();

    Long getTotalTasksCount();

    Long getTotalEmployeesCount();
}
