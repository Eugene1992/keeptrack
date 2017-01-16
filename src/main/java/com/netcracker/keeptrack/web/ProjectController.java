package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.service.ProjectService;
import com.netcracker.keeptrack.service.UserService;
import com.netcracker.keeptrack.web.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import javax.validation.Valid;

/**
 * Return Apache Tiles 'home' definition by '/' GET request.
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @ModelAttribute("project")
    public ProjectDTO construct() {
        return new ProjectDTO();
    }

    /**
     * Employees tab controller.
     *
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public String addProject(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult result, Model model) {
        final List<User> FREE_EMPLOYEES = userService.getFreeEmployees();
        final List<User> FREE_MANAGERS = userService.getFreeManagers();
        if (result.hasErrors()) {
            model.addAttribute("freeEmployees", FREE_EMPLOYEES);
            model.addAttribute("freeManagers", FREE_MANAGERS);
            return "new-project";
        }
        projectService.addProject(project);
        return "redirect:projects";
    }

    /**
     * Projects menu controller.
     *
     * @param model projects info
     * @return tiles 'projects' definition
     */
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projects(Model model) {
        final List<Project> PROJECTS = projectService.getAllProjects();

        model.addAttribute("projects", PROJECTS);
        return "projects";
    }

    /**
     * Project profile controller.
     *
     * @param model selected project info
     * @param name selected project name
     * @return tiles 'project' definition
     */
    @RequestMapping(value = "/project/{name}", method = RequestMethod.GET)
    public String project(Model model, @PathVariable String name) {
        final Project CURRENT_PROJECT = projectService.getProjectByName(name);
        final Integer PROJECT_ID = CURRENT_PROJECT.getId();
        final User PM = projectService.getProjectManager(PROJECT_ID);
        final Long TOTAL_PROJECT_EMPLOYEES = projectService.getProjectEmployeesCount(PROJECT_ID);
        final Long TOTAL_PROJECT_SPRINTS = projectService.getProjectSprintsCount(PROJECT_ID);
        final List<User> FREE_EMPLOYEES = userService.getFreeEmployees();
        model.addAttribute("freeEmployees", FREE_EMPLOYEES);
        model.addAttribute("currentProject", CURRENT_PROJECT);
        model.addAttribute("projectManager", PM);
        model.addAttribute("totalProjectEmployees", TOTAL_PROJECT_EMPLOYEES);
        model.addAttribute("totalProjectSprints", TOTAL_PROJECT_SPRINTS);
        return "project";
    }

    /**
     * Projects menu controller.
     *
     * @param model projects info
     * @return tiles 'projects' definition
     */
    @RequestMapping(value = "/projects/new", method = RequestMethod.GET)
    public String newProject(Model model) {
        final List<User> FREE_EMPLOYEES = userService.getFreeEmployees();
        final List<User> FREE_MANAGERS = userService.getFreeManagers();
        model.addAttribute("freeEmployees", FREE_EMPLOYEES);
        model.addAttribute("freeManagers", FREE_MANAGERS);
        return "new-project";
    }

    /**
     * Delete employee from current project.
     *
     * @param id of the employee which will be deleted
     * @param name of the employee project
     * @return tiles 'project' definition
     */
    @RequestMapping(value = "project/employees/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam("employeeId") String id,
                                 @RequestParam("projectName") String name) {
        final Integer EMPLOYEE_ID = Integer.parseInt(id);
        projectService.deleteEmployeeFormProject(EMPLOYEE_ID);
        return "redirect:/project/" + name;
    }

    /**
     * Delete employee from current project.
     *
     * @param employeeId of the employee which will be deleted
     * @param projectName of the employee project
     * @return tiles 'project' definition
     */
    @RequestMapping(value = "project/employees/add", method = RequestMethod.POST)
    public String addEmployee(@RequestParam("employeeId") String employeeId,
                              @RequestParam("projectName") String projectName) {
        final Integer EMPLOYEE_ID = Integer.parseInt(employeeId);
        projectService.addEmployeeToProject(EMPLOYEE_ID, projectName);
        return "redirect:/project/" + projectName;
    }
}
