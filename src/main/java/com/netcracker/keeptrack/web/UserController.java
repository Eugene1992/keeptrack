package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.model.Gender;
import com.netcracker.keeptrack.model.Role;
import com.netcracker.keeptrack.model.User;
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
public class UserController {

    private List<User> users;

    {
        users = new ArrayList<>();
        /*users.add(new User("legendary", "qwerty", Role.ADMIN, "Evgeniy", "Deyneka", 1,
                "deyneko55@gmail.com", Gender.MALE, LocalDate.now(), LocalDate.now()));
        users.add(new User("kinder", "qwerty", Role.EMPLOYEE, "Dmitriy", "Titov", 1,
                "titov5@gmail.com", Gender.MALE, LocalDate.now(), LocalDate.now()));
        users.add(new User("zayka", "qwerty", Role.ADMIN, "Anna", "Zaika", 1,
                "zayka@gmail.com", Gender.FEMALE, LocalDate.now(), LocalDate.now()));*/
    }

    @ModelAttribute("user")
    public User construct() {
        return new User();
    }

    /**
     * Employees tab controller.
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, BindingResult result) {
        users.add(user);
        return "redirect:users";
    }

    /**
     * Employees tab controller.
     * @param model users info
     * @return tiles 'users' definition
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String employees(Model model) {
        model.addAttribute("usersList", users);
        return "users";
    }
}
