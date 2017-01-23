package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.BaseTestConfig;
import com.netcracker.keeptrack.model.Role;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.web.dto.UserDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceTest extends BaseTestConfig {

    @Autowired
    private UserService userService;

    private UserDTO user;

    @Before
    public void setUp() throws Exception {
        user = new UserDTO();
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setUsername("vano777");
        user.setEmail("ivanov@gmail.com");
        user.setRole("EMPLOYEE");
        user.setGender("MALE");
        user.setBirthday("1992-05-21");
        user.setHireDay("2016-02-12");
        user.setPassword("qwerty");
        user.setSalary("10000");
        user.setProjectId("1");
    }

    @Test
    public void addUserTest() throws Exception {
        userService.addUser(user);
        User savedUser = userService.getUserByUsername(user.getUsername());
        Assert.assertNotNull(savedUser);
        Assert.assertEquals(savedUser.getFirstName(), user.getFirstName());
        Assert.assertEquals(savedUser.getEmail(), user.getEmail());
    }

    @Test
    public void deleteUserTest() throws Exception {
        final Integer DELETED_USER_ID = 1;
        final User USER_BEFORE_DELETING = userService.getUserById(DELETED_USER_ID);
        Assert.assertNotNull(USER_BEFORE_DELETING);
        userService.deleteUser(DELETED_USER_ID);
        final User USER_AFTER_DELETING = userService.getUserById(DELETED_USER_ID);
        Assert.assertNull(USER_AFTER_DELETING);
    }

    @Test
    public void getUserByIdTest() throws Exception {
        final Integer USER_ID = 2;
        final String USER_FIRST_NAME = "Roman";
        final String USER_EMAIL = "randriyanov@gmail.com";
        final User RESULT_USER = userService.getUserById(USER_ID);
        Assert.assertNotNull(RESULT_USER);
        Assert.assertEquals(USER_FIRST_NAME, RESULT_USER.getFirstName());
        Assert.assertEquals(USER_EMAIL, RESULT_USER.getEmail());
    }

    @Test
    public void getUserByUsernameTest() throws Exception {
        final String USER_USERNAME = "freddy";
        final User RESULT_USER = userService.getUserByUsername(USER_USERNAME);
        Assert.assertNotNull(RESULT_USER);
        Assert.assertEquals(USER_USERNAME, RESULT_USER.getUsername());
    }

    @Test
    public void updateUserTest() throws Exception {
        final User USER = userService.getUserById(1);
        final String USERNAME = USER.getUsername();
        final String NEW_USERNAME = "mankunianeth";
        USER.setUsername(NEW_USERNAME);
        userService.updateUser(new UserDTO(USER));
        final User UPDATED_USER = userService.getUserById(1);
        final String UPDATED_USERNAME = UPDATED_USER.getUsername();
        Assert.assertNotNull(UPDATED_USER);
        Assert.assertNotEquals(USERNAME, UPDATED_USERNAME);
        Assert.assertEquals(NEW_USERNAME, UPDATED_USERNAME);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        final Integer EXPECTED_SIZE = 28;
        final List<User> ALL_USERS = userService.getAllUsers();
        final Integer RESULT_SIZE = ALL_USERS.size();
        Assert.assertNotNull(ALL_USERS);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getFreeEmployeesTest() throws Exception {
        final Integer EXPECTED_SIZE = 5;
        final List<User> FREE_EMPLOYEES = userService.getFreeEmployees();
        final Integer RESULT_SIZE = FREE_EMPLOYEES.size();
        Assert.assertNotNull(FREE_EMPLOYEES);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        final Integer EXPECTED_SIZE = 18;
        final List<User> ALL_EMPLOYEES = userService.getAllEmployees();
        final Integer RESULT_SIZE = ALL_EMPLOYEES.size();
        Assert.assertNotNull(ALL_EMPLOYEES);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getFreeManagersTest() throws Exception {
        final Integer EXPECTED_SIZE = 5;
        final List<User> FREE_MANAGERS = userService.getFreeManagers();
        final Integer RESULT_SIZE = FREE_MANAGERS.size();
        Assert.assertNotNull(FREE_MANAGERS);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getAllManagersTest() throws Exception {
        final Integer EXPECTED_SIZE = 8;
        final List<User> ALL_MANAGERS = userService.getAllManagers();
        final Integer RESULT_SIZE = ALL_MANAGERS.size();
        Assert.assertNotNull(ALL_MANAGERS);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void deleteEmployeeFormProjectTest() throws Exception {
        final Integer EMPLOYEE_ID = 5;
        final User RESULT_USER = userService.getUserById(EMPLOYEE_ID);
        Assert.assertNotNull(RESULT_USER.getProject());
        Assert.assertEquals(Role.EMPLOYEE, RESULT_USER.getRole());
        userService.deleteEmployeeFormProject(EMPLOYEE_ID.toString());
        Assert.assertNotNull(RESULT_USER.getProject());
    }

    @Test
    public void addEmployeeToProjectTest() throws Exception {
        final Integer EMPLOYEE_ID = 17;
        final String PROJECT_NAME = "Minerva";
        final User EMPLOYEE = userService.getUserById(EMPLOYEE_ID);
        Assert.assertNull(EMPLOYEE.getProject());
        Assert.assertEquals(Role.EMPLOYEE, EMPLOYEE.getRole());
        userService.addEmployeeToProject(EMPLOYEE_ID.toString(), PROJECT_NAME);
        final User UPD_EMPLOYEE = userService.getUserById(EMPLOYEE_ID);
        Assert.assertNotNull(UPD_EMPLOYEE.getProject());
        Assert.assertEquals(UPD_EMPLOYEE.getProject().getName(), PROJECT_NAME);
    }
}