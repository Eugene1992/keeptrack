package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        taskService.deleteTaskFromSprint(taskId);
        return "redirect:/project/" + projectName;
    }
}
