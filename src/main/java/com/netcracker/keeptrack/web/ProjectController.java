package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Return Apache Tiles 'home' definition by '/' GET request.
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ModelAttribute("project")
    public Project construct() {
        return new Project();
    }

    /**
     * Employees tab controller.
     *
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public String register(@ModelAttribute("project") Project project, BindingResult result) {
        return "redirect:projects";
    }

    /**
     * Employees tab controller.
     *
     * @param model users info
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projects(Model model) {
        final Project CURRENT_PROJECT = projectService.getProjectById(1);
        final User PM = projectService.getProjectManager(1);
        final Long TOTAL_PROJECT_EMPLOYEES = projectService.getProjectEmployeesCount(1);
        final Long TOTAL_PROJECT_SPRINTS = projectService.getProjectSprintsCount(1);
        model.addAttribute("currentProject", CURRENT_PROJECT);
        model.addAttribute("projectManager", PM);
        model.addAttribute("totalProjectEmployees", TOTAL_PROJECT_EMPLOYEES);
        model.addAttribute("totalProjectSprints", TOTAL_PROJECT_SPRINTS);
        System.out.println(CURRENT_PROJECT.getUsers());
        return "projects";
    }
}
