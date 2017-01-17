package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Sprint entity Controller.
 * Receives and processes requests associated with {@code Sprint} entity
 *
 * @see com.netcracker.keeptrack.model.Sprint
 * @see com.netcracker.keeptrack.service.SprintService
 */
@Controller
public class SprintController {

    @Autowired
    private SprintService sprintService;

    /**
     * Delete sprint from current project.
     * Will also removed(set to the CLOSED status) all tasks for this sprint.
     * Takes parameters from the form and passes them to the service layer.
     * After adding redirect to the page of the current project.
     *
     * @param projectName name of the sprint project
     * @param sprintName name of the sprint
     * @param sprintEndDate estimated finish date for the sprint
     * @param sprintDescription description of the sprint
     * @return redirect to current project
     */
    @RequestMapping(value = "project/sprints/add", method = RequestMethod.POST)
    public String addSprintToProject(@RequestParam("projectName") String projectName,
                                     @RequestParam("sprintName") String sprintName,
                                     @RequestParam("sprintEndDate") String sprintEndDate,
                                     @RequestParam("sprintDescription") String sprintDescription) {
        sprintService.addSprintToProject(projectName, sprintName, sprintEndDate, sprintDescription);
        return "redirect:/project/" + projectName;
    }

    /**
     * Delete sprint from current project.
     * Takes parameters from the form and passes them to the service layer.
     * After deleting redirect to the page of the current project.
     *
     * @param id id of the sprint
     * @param name name the sprint
     * @return redirect to current project
     */
    @RequestMapping(value = "project/sprints/delete", method = RequestMethod.POST)
    public String deleteSprintFromProject(@RequestParam("sprintId") String id,
                                          @RequestParam("projectName") String name) {
        Integer sprintId = Integer.parseInt(id);
        sprintService.deleteSprintFormProject(sprintId);
        return "redirect:/project/" + name;
    }
}
