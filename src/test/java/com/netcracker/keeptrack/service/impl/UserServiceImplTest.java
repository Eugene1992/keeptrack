package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.*;
import com.netcracker.keeptrack.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

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
                .addScript("db_scripts/projects.sql")
                .addScript("db_scripts/employees.sql")
                .addScript("db_scripts/managers.sql")
                .addScript("db_scripts/sprints.sql")
                .addScript("db_scripts/tasks.sql")
                .build();
    }

    @Test
    public void getUserByIdTest() throws Exception {
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}