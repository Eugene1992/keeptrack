package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Employee;
import com.netcracker.keeptrack.model.Gender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Return Apache Tiles 'home' definition by '/' GET request.
 */
@Controller
public class HomeController {

    /**
     * Home page controller.
     * @return tiles 'home' definition
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    /**
     * Employees tab controller.
     * @param model employees info
     * @return tiles 'employees' definition
     */
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(Model model) {
        final int SALARY = 10000;
        List<Employee> employees = Arrays.asList(
                new Employee("Evgeniy", "Deyneka", SALARY, "deyneko55@gmail.com",
                        Gender.MALE, LocalDate.now(), LocalDate.now()),
                new Employee("Anna", "Zaika", SALARY, "zaika55@gmail.com",
                        Gender.FEMALE, LocalDate.now(), LocalDate.now()),
                new Employee("Roman", "Andriyanov", SALARY, "randriyanov@gmail.com",
                        Gender.MALE, LocalDate.now(), LocalDate.now())
        );
        model.addAttribute("employeesList", employees);
        return "employees";
    }

    @RequestMapping(value = "/dashboards", method = RequestMethod.GET)
    public String dashboards() {
        return "dashboards";
    }
}
