package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.TaskRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link StatisticService} interface that provides methods for
 * statistic business logic.
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Returns the total number of customers in the system.
     * @see StatisticService#getTotalCustomersCount
     *
     * @return total number of customers
     */
    @Override
    public Long getTotalCustomersCount() {
        return userRepository.getTotalCustomersCount();
    }

    /**
     * Returns the total number of projects in the system.
     * @see StatisticService#getTotalProjectsCount
     *
     * @return total number of projects
     */
    @Override
    public Long getTotalProjectsCount() {
        return projectRepository.getTotalProjectsCount();
    }

    /**
     * Returns the total number of tasks in the system.
     * @see StatisticService#getTotalTasksCount
     *
     * @return total number of tasks
     */
    @Override
    public Long getTotalTasksCount() {
        return taskRepository.getTotalTasksCount();
    }

    /**
     * Returns the total number of employees in the system.
     * @see StatisticService#getTotalEmployeesCount
     *
     * @return total number of employees
     */
    @Override
    public Long getTotalEmployeesCount() {
        return userRepository.getTotalEmployeesCount();
    }

    /**
     * Returns the latest by date specified number of tasks.
     * @see StatisticService#getLatestTasks
     *
     * @param limit number of required tasks
     * @return list of latest tasks
     */
    @Override
    public List<Task> getLatestTasks(Integer limit) {
        return taskRepository.getLatestTasks(new PageRequest(0, limit));
    }
}
