package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.*;
import com.netcracker.keeptrack.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.Collections;

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

    private User testUser;

    private Integer userId;

    private Integer nonExistentUserId;

    private String email;

    private EmbeddedDatabase db;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db_scripts/create-db.sql")
                .build();
    }

    @Test
    public void getUserByIdTest() throws Exception {
        User userById = userService.getUserById(1);
        System.out.println(userById);
    }

    /*@Test
    public void getUserByNonExistentIdTest() throws Exception {
        final User RESULT = userService.getUserById(nonExistentUserId);
        Assert.assertNull(RESULT);
    }

    @Test
    public void deleteUserTest() throws Exception {
        userService.deleteUser(userId);
        final User RESULT = userService.getUserById(userId);
        Assert.assertNull(RESULT);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteByNonExistentIdTest() throws Exception {
        userService.deleteUser(nonExistentUserId);
    }

    @Test
    public void editUserTest() throws Exception {
        final String NEW_USER_EMAIL = "deyneko@gmail.com";
        testUser.setEmail(NEW_USER_EMAIL);
        userService.editUser(testUser);
        final User RESULT = userService.getUserById(userId);
        Assert.assertEquals(testUser.getEmail(), RESULT.getEmail());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void editByNonExistentIdTest() throws Exception {
        testUser.setId(nonExistentUserId);
        userService.editUser(testUser);
    }*/

    @After
    public void tearDown() {
        db.shutdown();
    }
}