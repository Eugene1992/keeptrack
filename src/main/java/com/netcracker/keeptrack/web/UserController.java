package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.service.ProjectService;
import com.netcracker.keeptrack.service.UserService;
import com.netcracker.keeptrack.service.validators.UserValidator;
import com.netcracker.keeptrack.web.dto.UserDTO;
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
 * User entity controller.
 * Receives and processes requests associated with {@code User} entity.
 *
 * @see com.netcracker.keeptrack.model.User
 * @see com.netcracker.keeptrack.service.UserService
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserValidator validator;

    @ModelAttribute("user")
    public UserDTO constructUser() {
        return new UserDTO();
    }

    /**
     * The controller to redirect to the menu of users, which provides CRUD
     * operations available for the administrator.
     *
     * @param model contains data about the all projects for HTML select menu
     *              and data about all users for table representing
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allProjects", projects);
        return "users";
    }

    /**
     * The controller for adding new users.
     * Validates the incoming data using Validator and ModelAttribute.
     * If the data is correct, it is passed to the service layer via Data Transfer Object
     * for further processing, if not - redirects to a separate form with validation errors.
     *
     * @param userDTO contains data obtained from spring form
     * @param model contains data about the all projects  for HTML select menu
     * @return redirect to users menu
     */
    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addNewUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        List<Project> projects = projectService.getAllProjects();
        validator.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("allProjects", projects);
            return "new-user";
        }
        userService.addUser(userDTO);
        return "redirect:/users";
    }

    /**
     * The controller that redirects to the update menu of the selected user.
     *
     * @param id selected user id
     * @param model contains data about the all projects for HTML select menu and updated
     *              task data for user changes
     * @return update menu of the selected user
     */
    @RequestMapping(value = "/users/update/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") String id, Model model) {
        List<Project> projects = projectService.getAllProjects();
        User user = userService.getUserById(Integer.valueOf(id));
        model.addAttribute("allProjects", projects);
        model.addAttribute("updUser", user);
        return "upd-user";
    }

    /**
     * The controller that processes data received from update form, validates and if data
     * is correct - transmits it to the service layer for further processing and saving.
     *
     * @param model contains data about the all projects for HTML select menu and updated
     *              task data for user changes
     * @return redirects to the tasks menu
     */
    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {
        List<Project> projects = projectService.getAllProjects();
        validator.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("allProjects", projects);
            return "upd-user";
        }
        userService.updateUser(userDTO);
        return "redirect:/users";
    }

    /**
     * The controller that delete user from data base.
     * The operation is available for the administrator.
     *
     * @param id of the user which will be deleted
     * @return redirect to the users menu
     */
    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("id") String id) {
        userService.deleteUser(Integer.valueOf(id));
        return "redirect:/users";
    }

    /**
     * The controller that redirects to the selected user profile.
     *
     * @param model selected user data
     * @return selected user profile view
     */
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public String getUserProfile(@PathVariable("username") String username, Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "user-profile";
    }
}
