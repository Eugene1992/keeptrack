package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.service.SprintService;
import com.netcracker.keeptrack.service.TaskService;
import com.netcracker.keeptrack.service.UserService;
import com.netcracker.keeptrack.service.validators.TaskValidator;
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

import java.util.List;
import javax.validation.Valid;

/**
 * Task entity Controller.
 * Receives and processes requests associated with {@code Task} entity
 *
 * @see com.netcracker.keeptrack.model.Task
 * @see TaskService
 */
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private SprintService sprintService;

    @Autowired
    private TaskValidator validator;

    @ModelAttribute("task")
    public TaskDTO construct() {
        return new TaskDTO();
    }

    /**
     * The controller to redirect to the menu of tasks, which provides CRUD
     * operations available for the administrator.
     *
     * @param model contains data about the all employees, project managers, sprints
     *              HTML select menu and data about all tasks for table representing
     * @return tiles 'tasks' definition
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String getAllProjects(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        List<Sprint> allSprints = sprintService.getAllSprints();
        List<User> allEmployees = userService.getAllEmployees();
        List<User> allManagers = userService.getAllManagers();
        model.addAttribute("tasksList", tasks);
        model.addAttribute("allSprints", allSprints);
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allManagers", allManagers);
        return "tasks";
    }

    /**
     * A controller for adding new tasks.
     * Validates the incoming data using Validator and ModelAttribute.
     * If the data is correct, it is passed to the service layer via Data Transfer Object
     * for further processing, if not - redirects to a separate form with validation errors.
     *
     * @param taskDTO contains data obtained from spring form
     * @param model contains data about the all employees, project managers and sprints for HTML select menu
     * @return redirect to created task profile
     */
    @RequestMapping(value = "/tasks/add", method = RequestMethod.POST)
    public String addNewTask(@Valid @ModelAttribute("task") TaskDTO taskDTO, BindingResult result, Model model) {
        List<Sprint> allSprints = sprintService.getAllSprints();
        List<User> allEmployees = userService.getAllEmployees();
        List<User> allManagers = userService.getAllManagers();
        validator.validate(taskDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("allSprints", allSprints);
            model.addAttribute("allEmployees", allEmployees);
            model.addAttribute("allManagers", allManagers);
            return "add-task";
        }
        taskService.addTask(taskDTO);
        return "redirect:/tasks";
    }

    /**
     * Controller that redirects to the update menu of the selected task.
     *
     * @param model contains data about the all employees, project managers, sprints for HTML select menu
     *              and updated task data for user changes
     * @return update menu of the selected task
     */
    @RequestMapping(value = "/tasks/update/{id}", method = RequestMethod.POST)
    public String updateTask(@PathVariable("id") String id, Model model) {
        Task task = taskService.getTaskById(Integer.valueOf(id));
        List<Sprint> allSprints = sprintService.getAllSprints();
        List<User> allEmployees = userService.getAllEmployees();
        List<User> allManagers = userService.getAllManagers();
        model.addAttribute("updTask", task);
        model.addAttribute("allSprints", allSprints);
        model.addAttribute("allEmployees", allEmployees);
        model.addAttribute("allManagers", allManagers);
        return "upd-task";
    }

    /**
     * Controller that processes data received from update form, validates and if data
     * is correct - transmits it to the service layer for further processing and saving.
     *
     * @param model contains data about the all employees, project managers, sprints for HTML select menu
     *              and updated task data for user changes
     * @return redirects to the tasks menu
     */
    @RequestMapping(value = "/tasks/update", method = RequestMethod.POST)
    public String updateTask(@Valid @ModelAttribute("task") TaskDTO taskDTO, BindingResult result, Model model) {
        List<Sprint> allSprints = sprintService.getAllSprints();
        List<User> allEmployees = userService.getAllEmployees();
        List<User> allManagers = userService.getAllManagers();
        validator.validate(taskDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("allSprints", allSprints);
            model.addAttribute("allEmployees", allEmployees);
            model.addAttribute("allManagers", allManagers);
            return "upd-task";
        }
        taskService.updateTask(taskDTO);
        return "redirect:/tasks";
    }

    /**
     * The controller that delete task from data base.
     * The operation is available for the administrator.
     *
     * @param id of the task which will be deleted
     * @return redirect to the tasks menu
     */
    @RequestMapping(value = "/tasks/delete", method = RequestMethod.POST)
    public String deleteTask(@RequestParam("id") String id) {
        taskService.deleteTask(Integer.valueOf(id));
        return "redirect:/tasks";
    }

    /**
     * Add task to current project.
     * Takes parameters from the form and passes them to the service layer.
     * After adding redirect to the page of the current project.
     *
     * @param assignerId id of the employee which will be assigned for the task
     * @param taskName name of the new task
     * @param taskEstimate estimate of the new task
     * @param taskEndDate the estimated finish date for the task
     * @param taskDescription description of the new task
     * @param sprintId id of the phase to which the task will be placed
     * @param projectName name of the current project
     * @return redirect to current project
     */
    @RequestMapping(value = "project/task/add", method = RequestMethod.POST)
    public String addTaskToSprint(@RequestParam("assignerId") String assignerId,
                                  @RequestParam("taskName") String taskName,
                                  @RequestParam("taskEstimate") String taskEstimate,
                                  @RequestParam("taskEndDate") String taskEndDate,
                                  @RequestParam("taskDescription") String taskDescription,
                                  @RequestParam("sprintId") String sprintId,
                                  @RequestParam("projectName") String projectName) {
        taskService.addTaskToSprint(taskName, taskEndDate, taskEstimate, assignerId, taskDescription, sprintId);
        return "redirect:/project/" + projectName;
    }

    /**
     * Delete task from current sprint.
     * Takes parameters from the form and passes them to the service layer.
     * After deleting redirect to the page of the current project.
     *
     * @param taskId id of the selected task
     * @param projectName name of the current project
     * @return redirect to current project
     */
    @RequestMapping(value = "project/tasks/delete", method = RequestMethod.POST)
    public String deleteTaskFromSprint(@RequestParam("taskId") String taskId,
                                       @RequestParam("projectName") String projectName) {
        taskService.deleteTaskFromSprint(Integer.valueOf(taskId));
        return "redirect:/project/" + projectName;
    }

    /**
     * Controller that redirects to the selected task profile.
     *
     * @param model selected task data
     * @return selected task profile view
     */
    @RequestMapping(value = "/task/{name}", method = RequestMethod.GET)
    public String getTaskProfile(@PathVariable("name") String name, Model model) {
        Task task = taskService.getTaskByName(name);
        model.addAttribute("task", task);
        return "task-profile";
    }
}
