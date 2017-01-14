package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.*;
import com.netcracker.keeptrack.service.TaskService;
import com.netcracker.keeptrack.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
})
@ActiveProfiles("hsql")
public class UserServiceImplTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private UserService userService;

    private EntityManager em;

    private User user;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        user = new User("legendary", "qwerty", Role.ADMIN, "Evgeniy", "Deyneka", 10000,
                "deyneko55@gmail.com", Gender.MALE, LocalDate.now(), LocalDate.now());

    }

    @Test
    public void addTask() throws Exception {
        userService.addUser(user);
    }
}