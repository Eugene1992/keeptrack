package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.repository.StatisticRepository;
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
    private StatisticRepository statisticRepository;

    @Override
    public Long getTotalCustomersCount() {
        return statisticRepository.getTotalCustomersCount();
    }

    @Override
    public Long getTotalProjectsCount() {
        return statisticRepository.getTotalProjectsCount();
    }

    @Override
    public Long getTotalTasksCount() {
        return statisticRepository.getTotalTasksCount();
    }

    @Override
    public Long getTotalEmployeesCount() {
        return statisticRepository.getTotalEmployeesCount();
    }

    @Override
    public List<Task> getLatestTasks(Integer limit) {
        return statisticRepository.getLatestTasks(new PageRequest(0, limit));
    }
}
