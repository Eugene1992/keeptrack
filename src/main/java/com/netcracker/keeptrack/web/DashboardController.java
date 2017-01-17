package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Return Apache Tiles 'home' definition by '/' GET request.
 */
@Controller
public class DashboardController {

    @Autowired
    private StatisticRepository statisticRepository;

    /**
     * Employees tab controller.
     *
     * @param model users info
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/dashboards", method = RequestMethod.GET)
    public String employees(Model model) {
        Long totalCustomers = statisticRepository.getTotalCustomersCount();
        Long totalProjects = statisticRepository.getTotalProjectsCount();
        Long totalEmployees = statisticRepository.getTotalEmployeesCount();
        Long totalTasks = statisticRepository.getTotalTasksCount();
        model.addAttribute("totalCustomers", totalCustomers);
        model.addAttribute("totalProjects", totalProjects);
        model.addAttribute("totalEmployees", totalEmployees);
        model.addAttribute("totalTasks", totalTasks);
        return "dashboards";
    }
}
