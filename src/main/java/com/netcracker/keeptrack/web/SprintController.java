package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.service.ProjectService;
import com.netcracker.keeptrack.service.SprintService;
import com.netcracker.keeptrack.service.validators.SprintValidator;
import com.netcracker.keeptrack.web.dto.SprintDTO;
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

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SprintValidator validator;

    @ModelAttribute("sprint")
    public SprintDTO construct() {
        return new SprintDTO();
    }

    /**
     * The controller to redirect to the menu of sprints, which provides CRUD
     * operations available for the administrator.
     *
     * @param model contains data about the available sprints, projects for
     *              HTML select menu and data about all sprints for table representing
     * @return tiles 'sprints' definition
     */
    @RequestMapping(value = "/sprints", method = RequestMethod.GET)
    public String getAllSprints(Model model) {
        List<Sprint> sprints = sprintService.getAllSprints();
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("sprintsList", sprints);
        return "sprints";
    }

    /**
     * The controller for adding new sprints.
     * Validates the incoming data using Validator and ModelAttribute.
     * If the data is correct, it is passed to the service layer via Data Transfer Object
     * for further processing, if not - redirects to a separate form with validation errors.
     *
     * @param sprintDto contains data obtained from spring form
     * @param model contains data about the available sprints for HTML select menu
     * @return redirect to sprints menu
     */
    @RequestMapping(value = "/sprints/add", method = RequestMethod.POST)
    public String addNewSprint(@Valid @ModelAttribute("sprint") SprintDTO sprintDto, BindingResult result, Model model) {
        List<Project> projects = projectService.getAllProjects();
        validator.validate(sprintDto, result);
        if (result.hasErrors()) {
            model.addAttribute("projects", projects);
            return "new-sprint";
        }
        sprintService.addSprint(sprintDto);
        return "redirect:/sprints";
    }

    /**
     * The controller that redirects to the update menu of the selected sprint.
     *
     * @param id selected sprint identifier
     * @param model contains data about the all projects for HTML select menu
     *              and updated sprint data for user changes
     * @return update menu of the selected sprint
     */
    @RequestMapping(value = "/sprints/update/{id}", method = RequestMethod.POST)
    public String updateSprint(@PathVariable("id") String id, Model model) {
        Sprint sprint = sprintService.getSprintById(Integer.valueOf(id));
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("updSprint", sprint);
        model.addAttribute("projects", projects);
        return "upd-sprint";
    }

    /**
     * The controller that processes data received from update form, validates and if data
     * is correct - transmits it to the service layer for further processing and saving.
     *
     * @param model contains data about the all projects for HTML select menu
     *              and updated project data for sprint changes
     * @return redirects to the sprints menu
     */
    @RequestMapping(value = "/sprints/update", method = RequestMethod.POST)
    public String updateSprint(@Valid @ModelAttribute("sprint") SprintDTO sprintDto, BindingResult result, Model model) {
        Sprint sprint = sprintService.getSprintById(Integer.parseInt(sprintDto.getId()));
        List<Project> projects = projectService.getAllProjects();
        validator.validate(sprintDto, result);
        if (result.hasErrors()) {
            model.addAttribute("updSprint", sprint);
            model.addAttribute("projects", projects);
            return "upd-sprint";
        }
        sprintService.updateSprint(sprintDto);
        return "redirect:/sprints";
    }

    /**
     * The controller that delete sprint from data base.
     * The operation is available for the administrator.
     *
     * @param id of the sprint which will be deleted
     * @return redirect to the sprints menu
     */
    @RequestMapping(value = "/sprints/delete", method = RequestMethod.POST)
    public String deleteSprint(@RequestParam("id") String id) {
        sprintService.deleteSprint(Integer.valueOf(id));
        return "redirect:/sprints";
    }

    /**
     * The controller that redirects to the profile of the selected sprint which contains
     * full information about it.
     *
     * @param name of the selected project
     * @param model contains data about the selected sprint
     * @return selected sprint profile
     */
    @RequestMapping(value = "/sprint/{name}", method = RequestMethod.GET)
    public String getSprintProfile(@PathVariable("name") String name, Model model) {
        Sprint sprint = sprintService.getSprintByName(name);
        model.addAttribute("sprint", sprint);
        return "sprint-profile";
    }

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
        sprintService.deleteSprintFromProject(sprintId);
        return "redirect:/project/" + name;
    }
}
