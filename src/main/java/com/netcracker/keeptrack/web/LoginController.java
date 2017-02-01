package com.netcracker.keeptrack.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Login page controller.
 */
@Controller
public class LoginController {

    /**
     * Login page controller.
     * Redirects from root url to login page
     *
     * @return tiles 'login' definition
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/login";
    }

    /**
     * Login page controller.
     * Redirects to login page.
     *
     * @return tiles 'login' definition
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
        if (error != null) {
            modelMap.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    /**
     * Redirects to home page.
     *
     * @return tiles 'home' definition
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
