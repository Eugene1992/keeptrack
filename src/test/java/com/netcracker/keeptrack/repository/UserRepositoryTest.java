package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.BaseTestConfig;
import com.netcracker.keeptrack.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRepositoryTest extends BaseTestConfig {

    @Autowired
    private UserRepository userRepository;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String username;

    @Before
    public void setUp() throws Exception {
        userFirstName = "Evgeniy";
        userLastName = "Deyneka";
        userEmail = "deyneko55@gmail.com";
        username = "legendary";
    }

    @Test
    public void getFreeEmployeesTest() throws Exception {
        final List<User> RESULT_LIST = userRepository.getFreeEmployees();
        final Integer EXPECTED_SIZE = 5;
        final Integer RESULT_SIZE = RESULT_LIST.size();
        Assert.assertNotNull(RESULT_LIST);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getFreeManagersTest() throws Exception {
        final List<User> RESULT_LIST = userRepository.getFreeManagers();
        final Integer EXPECTED_SIZE = 5;
        final Integer RESULT_SIZE = RESULT_LIST.size();
        Assert.assertNotNull(RESULT_LIST);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getUserByFirstAndLastNameTest() throws Exception {
        final User RESULT_USER = userRepository.getUserByFirstAndLastName(userFirstName, userLastName);
        Assert.assertNotNull(RESULT_USER);
        Assert.assertEquals(userFirstName, RESULT_USER.getFirstName());
        Assert.assertEquals(userLastName, RESULT_USER.getLastName());
    }

    @Test
    public void getUserByUsernameTest() throws Exception {
        final User RESULT_USER = userRepository.getUserByUsername(username);
        Assert.assertNotNull(RESULT_USER);
        Assert.assertEquals(username, RESULT_USER.getUsername());
    }

    @Test
    public void getUserByEmail() throws Exception {
        final User RESULT_USER = userRepository.getUserByEmail(userEmail);
        Assert.assertNotNull(RESULT_USER);
        Assert.assertEquals(userEmail, RESULT_USER.getEmail());
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        final List<User> EMPLOYEES_LIST = userRepository.getAllEmployees();
        final Integer EXPECTED_SIZE = 18;
        final Integer RESULT_SIZE = EMPLOYEES_LIST.size();
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getAllManagersTest() throws Exception {
        final List<User> MANAGERS_LIST = userRepository.getAllManagers();
        final Integer EXPECTED_SIZE = 8;
        final Integer RESULT_SIZE = MANAGERS_LIST.size();
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getTotalCustomersCountTest() throws Exception {
        final Long RESULT_COUNT = userRepository.getTotalCustomersCount();
        final Long EXPECTED_COUNT = 1L;
        Assert.assertNotNull(RESULT_COUNT);
        Assert.assertEquals(EXPECTED_COUNT, RESULT_COUNT);
    }

    @Test
    public void getTotalEmployeesCountTest() throws Exception {
        final Long EXPECTED_COUNT = 18L;
        final Long RESULT_COUNT = userRepository.getTotalEmployeesCount();
        Assert.assertNotNull(RESULT_COUNT);
        Assert.assertEquals(EXPECTED_COUNT, EXPECTED_COUNT);
    }
}