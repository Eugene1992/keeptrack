package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Return Apache Tiles 'home' definition by '/' GET request.
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    /**
     * Login page controller.
     * @return tiles 'home' definition
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * Home page controller.
     * @return tiles 'home' definition
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Principal principal, Model model) {
        User loggedUser = userService.getUserByUsername(principal.getName());
        model.addAttribute("loggedUser", loggedUser);
        return "home";
    }
}
