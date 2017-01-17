package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Return Apache Tiles 'home' definition by '/' GET request.
 */
@Controller
public class DashboardController {

    @Autowired
    private StatisticService statisticService;

    /**
     * Employees tab controller.
     *
     * @param model users info
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/dashboards", method = RequestMethod.GET)
    public String employees(Model model) {
        final Integer LATEST_TASKS_LIMIT = 10;
        Long totalCustomers = statisticService.getTotalCustomersCount();
        Long totalProjects = statisticService.getTotalProjectsCount();
        Long totalEmployees = statisticService.getTotalEmployeesCount();
        Long totalTasks = statisticService.getTotalTasksCount();
        List<Task> latestTasks = statisticService.getLatestTasks(LATEST_TASKS_LIMIT);
        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("totalProjects", totalProjects);
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("totalTasks", totalTasks);
        model.addAttribute("latestTasks", latestTasks);
        return "dashboards";
    }
}
