package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Sprint;
import org.junit.After;
import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
})
@ActiveProfiles("hsql")
public class SprintRepositoryTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private SprintRepository sprintRepository;

    private EntityManager em;

    private EmbeddedDatabase db;

    private String sprintName;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db_scripts/projects.sql")
                .addScript("db_scripts/sprints.sql")
                .build();

        sprintName = "Sprint 1";
    }

    @Test
    public void getSprintByName() throws Exception {
        final Sprint RESULT_SPRINT = sprintRepository.getSprintByName(sprintName);
        Assert.assertNotNull(RESULT_SPRINT);
        Assert.assertEquals(sprintName, RESULT_SPRINT.getName());
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}