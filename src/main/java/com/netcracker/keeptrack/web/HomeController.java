package com.netcracker.keeptrack.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Return Apache Tiles 'home' definition by '/' GET request.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees() {
        return "employees";
    }
}
