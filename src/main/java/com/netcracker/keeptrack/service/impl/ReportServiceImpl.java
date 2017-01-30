package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.SprintStatus;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.ProjectRepository;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.ReportService;
import com.netcracker.keeptrack.web.dto.EmployeeReportDTO;
import com.netcracker.keeptrack.web.dto.ProjectReportDTO;
import com.netcracker.keeptrack.web.dto.SprintReportDTO;
import com.netcracker.keeptrack.web.dto.TaskReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link ReportService} interface that provides methods for
 * Project and Employee report generation.
 *
 * @see ReportService
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * The number of working hours per day.
     */
    private static final int WORKING_HOURS_PER_DAY = 8;

    /**
     * Generates a report about the selected project.
     * Output DTO contains project summary info, tasks and sprints info, including
     * durations, deviations, human-hours, total non/delayed tasks and sprints numbers.
     *
     * @param projectName name of the selected project
     * @return project report data transfer object
     */
    @Override
    public ProjectReportDTO generateProjectReport(String projectName) {
        ProjectReportDTO projectReportDTO = new ProjectReportDTO();

        Project project = projectRepository.getProjectByName(projectName);
        projectReportDTO.setProject(project);

        List<TaskReportDTO> dtoTasks = getDtoTasks(project);
        projectReportDTO.setDtoTasks(dtoTasks);

        List<SprintReportDTO> dtoSprints = getDtoSprints(project);
        projectReportDTO.setDtoSprints(dtoSprints);

        long delayedTasksNum = getDelayedTasksNum(dtoTasks);
        projectReportDTO.setNumOfDelayedTasks(delayedTasksNum);

        long nonDelayedTasksNum = getNonDelayedTasksNum(dtoTasks);
        projectReportDTO.setNumOfNonDelayedTasks(nonDelayedTasksNum);

        long delayedSprintsNum = getDelayedSprintsNum(dtoSprints);
        projectReportDTO.setNumOfDelayedSprints(delayedSprintsNum);

        long nonDelayedSprintsNum = getNonDelayedSprintsNum(dtoSprints);
        projectReportDTO.setNumOfNonDelayedSprints(nonDelayedSprintsNum);

        return projectReportDTO;
    }

    /**
     * Generates a report about the selected employee.
     * Output DTO contains employee summary info, projects, phases, tasks in which the employee
     * participated at the specified time, total non/delayed tasks and sprints numbers
     *
     * @param username name of the selected employee
     * @param fromDate start date of report
     * @param toDate end date of the report
     * @return employee report data transfer object
     */
    @Override
    public EmployeeReportDTO generateEmployeeReport(String username, String fromDate, String toDate) {
        EmployeeReportDTO employeeReportDTO = new EmployeeReportDTO();

        User user = userRepository.getUserByUsername(username);
        employeeReportDTO.setReportedUser(user);

        List<Project> allProjects = projectRepository.findAll();
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);
        List<Project> employeeProjects = getEmployeeProjects(allProjects, user, from, to);
        employeeReportDTO.setProjects(employeeProjects);

        List<Sprint> employeeSprints = getEmployeeSprints(employeeProjects, user, from, to);
        employeeReportDTO.setSprints(employeeSprints);

        List<TaskReportDTO> employeeTasks = getEmployeeTasks(employeeProjects, user, from, to);
        employeeReportDTO.setDtoTasks(employeeTasks);

        int delayedTasksNum = (int) getDelayedTasksNum(employeeTasks);
        employeeReportDTO.setNumOfDelayedTasks(delayedTasksNum);

        int nonDelayedTasksNum = (int) getNonDelayedTasksNum(employeeTasks);
        employeeReportDTO.setNumOfNonDelayedTasks(nonDelayedTasksNum);

        return employeeReportDTO;
    }

    /**
     * Counts the number of tasks performed without any delay.
     *
     * @param tasks input tasks
     * @return number of non delayed tasks
     */
    private long getNonDelayedTasksNum(List<TaskReportDTO> tasks) {
        return tasks.stream()
                .filter(task -> task.getDeviation() >= 0)
                .count();
    }

    /**
     * Counts the number of tasks performed with a delay.
     *
     * @param tasks input tasks
     * @return number of delayed tasks
     */
    private long getDelayedTasksNum(List<TaskReportDTO> tasks) {
        return tasks.stream()
                .filter(task -> task.getDeviation() < 0)
                .count();
    }

    /**
     * Counts the number of sprints performed with a delay.
     *
     * @param sprints input sprints
     * @return number of delayed sprints
     */
    private long getDelayedSprintsNum(List<SprintReportDTO> sprints) {
        return sprints.stream()
                .filter(sprint -> sprint.getDeviation() < 0)
                .count();
    }

    /**
     * Counts the number of sprints performed without any delay.
     *
     * @param sprints input sprints
     * @return number of non delayed sprints
     */
    private long getNonDelayedSprintsNum(List<SprintReportDTO> sprints) {
        return sprints.stream()
                .filter(sprint -> sprint.getDeviation() >= 0)
                .count();
    }

    /**
     * Returns tasks in the specified project with task summary information,
     * duration, human hours, deviation.
     * @param project specified project
     * @return list of the DTO tasks
     */
    private List<TaskReportDTO> getDtoTasks(Project project) {
        return project.getSprints().stream()
                .filter(sprint -> sprint.getStatus() == SprintStatus.CLOSED)
                .flatMap(sprint -> sprint.getTasks().stream())
                .map(task -> new TaskReportDTO(
                        task,
                        getTaskDuration(task),
                        getTaskHumanHours(task),
                        getTaskDeviation(task)))
                .collect(Collectors.toList());
    }

    /**
     * Returns sprints in the specified project with sprint summary information,
     * duration, human hours, deviation.
     * @param project specified project
     * @return list of the DTO sprints
     */
    private List<SprintReportDTO> getDtoSprints(Project project) {
        return project.getSprints().stream()
                .filter(sprint -> sprint.getStatus() == SprintStatus.CLOSED)
                .map(sprint -> new SprintReportDTO(
                        sprint,
                        getSprintDuration(sprint),
                        getSprintHumanHours(sprint),
                        getSprintDeviation(sprint)))
                .collect(Collectors.toList());
    }

    /**
     * Calculates the duration of a task.
     *
     * @param task selected task
     * @return task duration in hours
     */
    private int getTaskDuration(Task task) {
        return getWeekdaysWorkingHours(task.getStartDate(), task.getEndDate());
    }

    /**
     * Calculates the human hours of a task.
     * The value and realization coincides with the duration as in this version
     * on a task may have only one employee
     *
     * @param task selected task
     * @return task human hours
     */
    private Integer getTaskHumanHours(Task task) {
        return getWeekdaysWorkingHours(task.getStartDate(), task.getEndDate());
    }

    /**
     * Calculates the deviation of a task.
     * The deviation is calculated as the difference between the estimated time
     * and actual spent hours(start and end dates interval).
     *
     * @param task selected task
     * @return task deviation in hours
     */
    private Integer getTaskDeviation(Task task) {
        LocalDate startDate = task.getStartDate();
        LocalDate endDate = task.getEndDate();
        int estimate = task.getEstimate();
        return estimate - getWeekdaysWorkingHours(startDate, endDate);
    }

    /**
     * Calculates the duration of a sprint.
     *
     * @param sprint selected sprint
     * @return sprint duration in hours
     */
    private Integer getSprintDuration(Sprint sprint) {
        return getWeekdaysWorkingHours(sprint.getStartDate(), sprint.getEndDate());
    }

    /**
     * Calculates the deviation of a sprint.
     * If the sprint contain a task, the end time of which is greater than the
     * end time of the sprint, calculate the difference between these dates in days.
     * Otherwise, specify the end date of period as end date of sprint.
     *
     * @param sprint selected sprint
     * @return sprint deviation in days
     */
    private Integer getSprintDeviation(Sprint sprint) {
        LocalDate latestDate = sprint.getTasks().stream()
                .filter(task -> task.getStatus() == TaskStatus.CLOSED)
                .filter(task -> task.getEndDate().isAfter(sprint.getEndDate()))
                .map(Task::getEndDate)
                .max(LocalDate::compareTo)
                .orElse(sprint.getEndDate());
        return -Period.between(sprint.getEndDate(), latestDate).getDays();
    }

    /**
     * Calculates the human hours of a sprint.
     * Summarize the number of hours spent on each closed task in sprint.
     *
     * @param sprint selected sprint
     * @return sprint human hours
     */
    private Integer getSprintHumanHours(Sprint sprint) {
        return sprint.getTasks().stream()
                .filter(task -> task.getStatus() == TaskStatus.CLOSED)
                .mapToInt(task -> getWeekdaysWorkingHours(task.getStartDate(), task.getEndDate()))
                .sum();
    }

    /**
     * Counts the number of workdays in date interval.
     *
     * @param start start date
     * @param end end date
     * @return total number of workdays
     */
    private int getWeekdaysWorkingHours(LocalDate start, LocalDate end) {
        int count = 0;
        DayOfWeek currDay;
        while (start.isBefore(end)) {
            currDay = start.getDayOfWeek();
            if (currDay != DayOfWeek.SATURDAY && currDay != DayOfWeek.SUNDAY) {
                count++;
            }
            start = start.plusDays(1);
        }
        return count * WORKING_HOURS_PER_DAY;
    }

    /**
     * Returns a list of projects in which participated the employee in the
     * specified date interval.
     *
     * @param projects input projects
     * @param user specified user
     * @param from start date
     * @param to end date
     * @return list of participated projects
     */
    private List<Project> getEmployeeProjects(List<Project> projects, User user, LocalDate from, LocalDate to) {
        return projects.stream()
                .filter(project -> project.getStartDate().isAfter(from) && project.getEndDate().isBefore(to))
                .filter(project -> project.getUsers().contains(user))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of sprints in which participated the employee in the
     * specified date interval.
     *
     * @param projects input projects
     * @param user specified user
     * @param from start date
     * @param to end date
     * @return list of participated sprints
     */
    private List<Sprint> getEmployeeSprints(List<Project> projects, User user, LocalDate from, LocalDate to) {
        return projects.stream()
                .flatMap(project -> project.getSprints().stream())
                .filter(sprint -> sprint.getTasks().stream()
                        .anyMatch(task -> task.getAssigner().equals(user)
                                       && task.getStartDate().isAfter(from)
                                       && task.getEndDate().isBefore(to)))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of tasks in which participated the employee in the
     * specified date interval.
     *
     * @param projects input projects
     * @param user specified user
     * @param from start date
     * @param to end date
     * @return list of participated tasks
     */
    private List<TaskReportDTO> getEmployeeTasks(List<Project> projects, User user, LocalDate from, LocalDate to) {
        return projects.stream()
                .flatMap(project -> getDtoTasks(project).stream())
                .filter(dto -> dto.getTask().getAssigner().equals(user)
                            && dto.getTask().getStartDate().isAfter(from)
                            && dto.getTask().getEndDate().isBefore(to))
                .collect(Collectors.toList());
    }
}
