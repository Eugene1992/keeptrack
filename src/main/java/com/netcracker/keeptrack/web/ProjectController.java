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
     * Add new project controller.
     *
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    public String addNewProject(@Valid @ModelAttribute("project") ProjectDTO project, BindingResult result, Model model) {
        List<User> freeEmployees = userService.getFreeEmployees();
        List<User> freeManagers = userService.getFreeManagers();
        String projectName = project.getName();
        if (result.hasErrors()) {
            model.addAttribute("freeEmployees", freeEmployees);
            model.addAttribute("freeManagers", freeManagers);
            return "new-project";
        }
        projectService.addProject(project);
        return "redirect:/project/" + projectName;
    }

    /**
     * Projects menu controller. Display list of all projects.
     *
     * @param model projects info
     * @return tiles 'projects' definition
     */
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();

        model.addAttribute("projects", projects);
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
        Project currentProject = projectService.getProjectByName(name);
        Integer projectId = currentProject.getId();
        User projectManager = projectService.getProjectManager(projectId);
        Long totalProjectEmployee = projectService.getProjectEmployeesCount(projectId);
        Long totalProjectSprints = projectService.getProjectSprintsCount(projectId);
        List<User> freeEmployees = userService.getFreeEmployees();
        model.addAttribute("freeEmployees", freeEmployees);
        model.addAttribute("currentProject", currentProject);
        model.addAttribute("projectManager", projectManager);
        model.addAttribute("totalProjectEmployees", totalProjectEmployee);
        model.addAttribute("totalProjectSprints", totalProjectSprints);
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
        List<User> freeEmployees = userService.getFreeEmployees();
        List<User> freeManagers = userService.getFreeManagers();
        model.addAttribute("freeEmployees", freeEmployees);
        model.addAttribute("freeManagers", freeManagers);
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
    public String deleteEmployeeFormProject(@RequestParam("employeeId") String id,
                                 @RequestParam("projectName") String name) {
        Integer employeeId = Integer.parseInt(id);
        userService.deleteEmployeeFormProject(employeeId);
        return "redirect:/project/" + name;
    }

    /**
     * Delete employee from current project.
     *
     * @param id of the employee which will be deleted
     * @param projectName of the employee project
     * @return tiles 'project' definition
     */
    @RequestMapping(value = "project/employees/add", method = RequestMethod.POST)
    public String addEmployeeToProject(@RequestParam("employeeId") String id,
                              @RequestParam("projectName") String projectName) {
        Integer employeeId = Integer.parseInt(id);
        userService.addEmployeeToProject(employeeId, projectName);
        return "redirect:/project/" + projectName;
    }
}
