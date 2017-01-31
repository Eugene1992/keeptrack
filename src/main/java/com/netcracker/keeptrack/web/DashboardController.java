package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.service.StatisticService;
import com.netcracker.keeptrack.service.UserService;
import com.netcracker.keeptrack.web.dto.ProjectStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * Dashboards menu controller.
*/
@Controller
public class DashboardController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private UserService userService;

    /**
     * Redirects to dashboards menu page.
     *
     * @param model data about total customers, employees, projects, tasks number
     * @return tiles 'dashboards' definition page
     */
    @RequestMapping(value = "/dashboards", method = RequestMethod.GET)
    public String dashboards(Model model) {
        final Integer TASKS_LIMIT = 10;
        final Integer EMPLOYEES_LIMIT = 10;
        Long totalCustomers = statisticService.getTotalCustomersCount();
        Long totalProjects = statisticService.getTotalProjectsCount();
        Long totalEmployees = statisticService.getTotalEmployeesCount();
        Long totalTasks = statisticService.getTotalTasksCount();
        List<Task> latestTasks = statisticService.getLatestTasks(TASKS_LIMIT);
        List<Task> latestEmployees = userService.getLatestHiredEmployees(EMPLOYEES_LIMIT);
        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("totalProjects", totalProjects);
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("totalTasks", totalTasks);
        model.addAttribute("latestTasks", latestTasks);
        model.addAttribute("latestEmployees", latestEmployees);
        return "dashboards";
    }

    /**
     * Redirects to project dashboards menu page.
     *
     * @param model data about project statistics
     * @return tiles 'dashboards' definition page
     */
    @RequestMapping(value = "/project/dashboards", method = RequestMethod.GET)
    public String projectDashboards(Model model, Principal principal) {
        Project project = userService.getUserByUsername(principal.getName()).getProject();
        ProjectStatsDTO projectStats = statisticService.getProjectStatistic(project);
        model.addAttribute("projectStats", projectStats);
        return "project-dashboards";
    }
}
