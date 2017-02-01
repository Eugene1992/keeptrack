package com.netcracker.keeptrack.service.impl;

import static java.time.temporal.ChronoUnit.DAYS;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.SprintStatus;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.TaskRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.StatisticService;
import com.netcracker.keeptrack.web.dto.ProjectStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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

    @Override
    public ProjectStatsDTO getProjectStatistic(Project project) {
        ProjectStatsDTO projectStats = new ProjectStatsDTO();

        long totalAssignedTasks = getTasksNumByStatus(project, TaskStatus.ASSIGNED);
        projectStats.setAssignedTasksNum(totalAssignedTasks);

        long totalRejectedTasks = getTasksNumByStatus(project, TaskStatus.REJECTED);
        projectStats.setRejectedTasksNum(totalRejectedTasks);

        long totalInProgressTasks = getTasksNumByStatus(project, TaskStatus.IN_PROGRESS);
        projectStats.setInProgressTasksNum(totalInProgressTasks);

        long totalClosedTasks = getTasksNumByStatus(project, TaskStatus.CLOSED);
        projectStats.setClosedTasksNum(totalClosedTasks);

        long totalInProgressSprints = getSprintsNumByStatus(project, SprintStatus.IN_PROGRESS);
        projectStats.setInProgressSprintsNum(totalInProgressSprints);

        long totalClosedSprints = getSprintsNumByStatus(project, SprintStatus.CLOSED);
        projectStats.setClosedSprintsNum(totalClosedSprints);

        final int TOTAL_PERCENT = 100;
        long totalDays = DAYS.between(project.getStartDate(), project.getEndDate());
        long spentDays = Period.between(LocalDate.now(), project.getEndDate()).getDays();
        long usedTimePercent = spentDays * TOTAL_PERCENT / totalDays;
        projectStats.setUsedTimePercent(usedTimePercent);

        return projectStats;
    }

    /**
     * Counts the total number of project tasks at the specified status.
     * @param project specified project
     * @param status specified status
     * @return total number of project tasks
     */
    private long getTasksNumByStatus(Project project, TaskStatus status) {
        return project.getSprints().stream()
                .flatMap(sprint -> sprint.getTasks().stream())
                .filter(task -> task.getStatus() == status)
                .count();
    }

    /**
     * Counts the total number of project sprints at the specified status.
     * @param project specified project
     * @param status specified status
     * @return total number of project sprints
     */
    private long getSprintsNumByStatus(Project project, SprintStatus status) {
        return project.getSprints().stream()
                .filter(sprint -> sprint.getStatus() == status)
                .count();
    }
}

