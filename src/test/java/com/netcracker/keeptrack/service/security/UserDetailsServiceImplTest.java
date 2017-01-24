package com.netcracker.keeptrack.service.security;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-security.xml",
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("hsql")
public class UserDetailsServiceImplTest {

    @Autowired
    private EntityManagerFactory emf;

    private EntityManager em;

    protected EmbeddedDatabase db;

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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void loadUserByUsername() throws Exception {
        final String USERNAME = "admin";
        UserDetails RESULT = userDetailsService.loadUserByUsername(USERNAME);
        Assert.assertNotNull(RESULT);
        Assert.assertEquals(USERNAME, RESULT.getUsername());
    }

}