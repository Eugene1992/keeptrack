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

    @Override
    public Long getTotalCustomersCount() {
        return userRepository.getTotalCustomersCount();
    }

    @Override
    public Long getTotalProjectsCount() {
        return projectRepository.getTotalProjectsCount();
    }

    @Override
    public Long getTotalTasksCount() {
        return taskRepository.getTotalTasksCount();
    }

    @Override
    public Long getTotalEmployeesCount() {
        return userRepository.getTotalEmployeesCount();
    }

    @Override
    public List<Task> getLatestTasks(Integer limit) {
        return taskRepository.getLatestTasks(new PageRequest(0, limit));
    }
}
