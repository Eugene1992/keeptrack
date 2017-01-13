package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Employee;
import com.netcracker.keeptrack.model.Gender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Return Apache Tiles 'home' definition by '/' GET request.
 */
@Controller
public class EmployeeController {

    private List<Employee> employees;

    {
        employees = new ArrayList<>();
        employees.add(new Employee("Evgeniy", "Deyneka", 1, "deyneko55@gmail.com",
                Gender.MALE, LocalDate.now(), LocalDate.now()));
        employees.add(new Employee("Anna", "Zaika", 1, "zaika55@gmail.com",
                Gender.FEMALE, LocalDate.now(), LocalDate.now()));
        employees.add(new Employee("Roman", "Andriyanov", 1, "randriyanov@gmail.com",
                Gender.MALE, LocalDate.now(), LocalDate.now()));
    }

    @ModelAttribute("employee")
    public Employee construct() {
        return new Employee();
    }

    /**
     * Employees tab controller.
     * @return tiles 'employees' definition
     */
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String register(@ModelAttribute("employee") Employee employee, BindingResult result) {
        employees.add(employee);
        return "employees";
    }

    /**
     * Employees tab controller.
     * @param model employees info
     * @return tiles 'employees' definition
     */
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(Model model) {
        model.addAttribute("employeesList", employees);
        return "employees";
    }
}
