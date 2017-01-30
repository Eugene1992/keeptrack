package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.ProjectService;
import com.netcracker.keeptrack.service.ReportService;
import com.netcracker.keeptrack.web.dto.EmployeeReportDTO;
import com.netcracker.keeptrack.web.dto.ProjectReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Report controller.
 * Receives and processes requests for report generation.
 */
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Redirects to reports menu.
     *
     * @param model contains data about all projects and employees for select representing
     * @return tiles 'reports' definition
     */
    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String reports(Model model) {
        List<Project> allProjects = projectService.getAllProjects();
        List<User> allEmployees = userRepository.getAllEmployees();
        model.addAttribute("projects", allProjects);
        model.addAttribute("employees", allEmployees);
        return "reports";
    }

    /**
     * Handles the request to create a report about the specified project.
     *
     * @param projectName name of specified project
     * @param model contains project report data
     * @return tiles 'project-report' definition
     */
    @RequestMapping(value = "/project/report", method = RequestMethod.GET)
    public String reportProject(@RequestParam(name = "name") String projectName, Model model) {
        ProjectReportDTO report = reportService.generateProjectReport(projectName);
        model.addAttribute("report", report);
        return "project-report";
    }

    /**
     * Handles the request to create a report about the specified employee.
     *
     * @param username of specified employee
     * @param fromDate interval start date
     * @param toDate interval end date
     * @param model contains employee report data
     * @return tiles 'employee-report' definition
     */
    @RequestMapping(value = "/employee/report", method = RequestMethod.GET)
    public String employeeProject(@RequestParam(name = "username") String username,
                                  @RequestParam(name = "fromDate") String fromDate,
                                  @RequestParam(name = "toDate") String toDate,
                                  Model model) {
        EmployeeReportDTO report = reportService.generateEmployeeReport(username, fromDate, toDate);
        model.addAttribute("report", report);
        return "employee-report";
    }
}
