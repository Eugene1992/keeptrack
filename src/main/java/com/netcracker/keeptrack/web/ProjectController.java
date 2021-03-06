package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Request;
import com.netcracker.keeptrack.model.RequestStatus;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.service.ProjectService;
import com.netcracker.keeptrack.service.UserService;
import com.netcracker.keeptrack.service.validators.ProjectValidator;
import com.netcracker.keeptrack.web.dto.ProjectDTO;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import com.netcracker.keeptrack.web.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

/**
 * Project entity controller.
 * Receives and processes requests associated with {@code Project} entity.
 *
 * @see com.netcracker.keeptrack.model.Project
 * @see com.netcracker.keeptrack.service.ProjectService
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectValidator validator;

    @ModelAttribute("project")
    public ProjectDTO constructProject() {
        return new ProjectDTO();
    }

    @ModelAttribute("sprint")
    public SprintDTO constructSprint() {
        return new SprintDTO();
    }

    @ModelAttribute("task")
    public TaskDTO constructTask() {
        return new TaskDTO();
    }

    /**
     * The controller to redirect to the menu of projects, which provides CRUD
     * operations available for the administrator.
     *
     * @param model contains data about the available employees, project managers for
     *              HTML select menu and data about all projects for table representing
     * @return tiles 'projects' definition
     */
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        List<User> freeManagers = userService.getFreeManagers();
        List<User> freeEmployees = userService.getFreeEmployees();
        model.addAttribute("freeManagers", freeManagers);
        model.addAttribute("freeEmployees", freeEmployees);
        model.addAttribute("projects", projects);
        return "projects";
    }

    /**
     * The controller for adding new projects.
     * Validates the incoming data using Validator and ModelAttribute.
     * If the data is correct, it is passed to the service layer via Data Transfer Object
     * for further processing, if not - redirects to a separate form with validation errors.
     *
     * @param projectDTO contains data obtained from spring form
     * @param model contains data about the available employees and project managers for HTML select menu
     * @return redirect to created project profile
     */
    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    public String addNewProject(@Valid @ModelAttribute("project") ProjectDTO projectDTO, BindingResult result, Model model) {
        List<User> freeEmployees = userService.getFreeEmployees();
        List<User> freeManagers = userService.getFreeManagers();
        validator.validate(projectDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("freeEmployees", freeEmployees);
            model.addAttribute("freeManagers", freeManagers);
            return "new-project";
        }
        projectService.addProject(projectDTO);
        return "redirect:/project/" + projectDTO.getName();
    }

    /**
     * The controller that redirects to the update menu of the selected project.
     *
     * @param id selected project identifier
     * @param model contains data about the all employees, project managers for HTML select menu
     *              and updated project data for user changes
     * @return update menu of the selected project
     */
    @RequestMapping(value = "/projects/update/{id}", method = RequestMethod.POST)
    public String updateProject(@PathVariable("id") String id, Model model) {
        Project project = projectService.getProjectById(Integer.valueOf(id));
        List<User> allEmployees = userService.getAllEmployees();
        List<User> allManagers = userService.getAllManagers();
        model.addAttribute("updProject", project);
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allManagers", allManagers);
        return "upd-project";
    }

    /**
     * The controller that processes data received from update form, validates and if data
     * is correct - transmits it to the service layer for further processing and saving.
     *
     * @param model contains data about the all employees, project managers for HTML select menu
     *              and updated project data for user changes
     * @return redirects to the projects menu
     */
    @RequestMapping(value = "/projects/update", method = RequestMethod.POST)
    public String updateProject(@Valid @ModelAttribute("project") ProjectDTO projectDTO, BindingResult result, Model model) {
        List<User> allEmployees = userService.getAllEmployees();
        List<User> allManagers = userService.getAllManagers();
        validator.validate(projectDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("allEmployees", allEmployees);
            model.addAttribute("allManagers", allManagers);
            return "upd-project";
        }
        projectService.updateProject(projectDTO);
        return "redirect:/projects";
    }

    /**
     * The controller that delete project from data base.
     * The operation is available for the administrator.
     *
     * @param id of the project which will be deleted
     * @return redirect to the projects menu
     */
    @RequestMapping(value = "/projects/delete", method = RequestMethod.POST)
    public String deleteProject(@RequestParam("id") String id) {
        projectService.deleteProject(Integer.valueOf(id));
        return "redirect:/projects";
    }

    /**
     * The controller that redirects to the profile of the selected project which contains
     * full information about it. In the the profile, project manager can add employees,
     * sprints and tasks into the project. Admin can remove them from the current project.
     *
     * @param name of the selected project
     * @param model contains data about the available employees, project managers and
     *              additional info about the project
     * @return selected project profile
     */
    @RequestMapping(value = "/project/{name}", method = RequestMethod.GET)
    public String project(@PathVariable String name, Model model) {
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
     * The controller that redirects to the profile of the selected project which contains
     * full information about it. In the the profile, project manager can add employees,
     * sprints and tasks into the project. Admin can remove them from the current project.
     *
     * @return selected project profile
     */
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String project(Model model, Principal principal) {
        User userData = userService.getUserByUsername(principal.getName());
        model.addAttribute("userData", userData);
        return "user-project";
    }

    /**
     * The controller that delete employee from current project.
     * The operation is available for the administrator.
     * He can also delete it from data base in projects menu.
     *
     * @param id of the employee which will be deleted
     * @param name of the current project
     * @return redirect to the current project profile
     */
    @RequestMapping(value = "project/employees/delete", method = RequestMethod.POST)
    public String deleteEmployeeFormProject(@RequestParam("employeeId") String id,
                                            @RequestParam("projectName") String name) {
        userService.deleteEmployeeFormProject(id);
        return "redirect:/project/" + name;
    }

    /**
     * The controller that add employee to the current project.
     * The operation is available for the administrator.
     *
     * @param id of the employee which will be added to the project
     * @param name of the current project
     * @return redirect to the current project profile
     */
    @RequestMapping(value = "project/employees/add", method = RequestMethod.POST)
    public String addEmployeeToProject(@RequestParam("employeeId") String id,
                                       @RequestParam("projectName") String name) {
        userService.addEmployeeToProject(id, name);
        return "redirect:/project/" + name;
    }

    /**
     * The controller that return specified project tasks.
     *
     * @param principal authorized user data object
     * @param model project created, assigned, closed, in progress, rejected tasks data
     * @return user tasks menu
     */
    @RequestMapping(value = "project/tasks", method = RequestMethod.GET)
    public String projectTasks(Principal principal, Model model) {
        User user = userService.getUserByUsername(principal.getName());
        List<Task> createdTasks = userService.getUserTasksByStatus(user, TaskStatus.CREATED);
        List<Task> assignedTasks = userService.getUserTasksByStatus(user, TaskStatus.ASSIGNED);
        List<Task> closedTasks = userService.getUserTasksByStatus(user, TaskStatus.CLOSED);
        List<Task> inProgressTasks = userService.getUserTasksByStatus(user, TaskStatus.IN_PROGRESS);
        List<Task> rejectedTasks = userService.getUserTasksByStatus(user, TaskStatus.REJECTED);
        model.addAttribute("createdTasks", createdTasks);
        model.addAttribute("assignedTasks", assignedTasks);
        model.addAttribute("closedTasks", closedTasks);
        model.addAttribute("inProgressTasks", inProgressTasks);
        model.addAttribute("rejectedTasks", rejectedTasks);
        return "user-tasks";
    }

    /**
     * The controller that return specified project requests.
     *
     * @param principal authorized user data object
     * @param model project opened, accepted, rejected requests data
     * @return user requests menu
     */
    @RequestMapping(value = "project/requests", method = RequestMethod.GET)
    public String projectRequests(Principal principal, Model model) {
        User user = userService.getUserByUsername(principal.getName());
        List<Request> openedRequests = userService.getUserRequestsByStatus(user, RequestStatus.OPENED);
        List<Request> acceptedRequests = userService.getUserRequestsByStatus(user, RequestStatus.ACCEPTED);
        List<Request> rejectedRequests = userService.getUserRequestsByStatus(user, RequestStatus.REJECTED);
        model.addAttribute("openedRequests", openedRequests);
        model.addAttribute("acceptedRequests", acceptedRequests);
        model.addAttribute("rejectedRequests", rejectedRequests);
        return "user-requests";
    }
}
