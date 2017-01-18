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
     * Add new project controller.
     *
     * @return tiles 'users' definition
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
     * Add new project controller.
     *
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/sprints/update/{id}", method = RequestMethod.POST)
    public String updateProject(@PathVariable("id") String id, Model model) {
        Integer sprintId = Integer.parseInt(id);
        Sprint sprint = sprintService.getSprintById(sprintId);
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("updSprint", sprint);
        model.addAttribute("projects", projects);
        return "upd-sprint";
    }

    /**
     * Add new project controller.
     *
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/sprints/update", method = RequestMethod.POST)
    public String updateSprint(@Valid @ModelAttribute("sprint") SprintDTO sprintDto, BindingResult result, Model model) {
        Integer sprintId = Integer.parseInt(sprintDto.getId());
        Sprint sprint = sprintService.getSprintById(sprintId);
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
     * Sprints menu controller. Display list of all sprints.
     *
     * @param model sprints info
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
     * Sprints menu controller. Display list of all sprints.
     *
     * @param model sprints info
     * @return tiles 'sprints' definition
     */
    @RequestMapping(value = "/sprint/{name}", method = RequestMethod.GET)
    public String getSprintProfile(@PathVariable("name") String name, Model model) {
        Sprint sprint = sprintService.getSprintByName(name);
        model.addAttribute("sprint", sprint);
        return "sprint-profile";
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

    /**
     * Delete sprint from database.
     * Takes parameters from the form and passes them to the service layer.
     * After deleting redirect to the sprints menu.
     *
     * @param id id of the sprint
     * @return redirect to current project
     */
    @RequestMapping(value = "/sprints/delete/{id}", method = RequestMethod.POST)
    public String deleteSprint(@PathVariable("id") String id) {
        Integer sprintId = Integer.parseInt(id);
        sprintService.deleteSprint(sprintId);
        return "redirect:/sprints";
    }
}
