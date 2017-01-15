package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.service.ProjectService;
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
public class ProjectServiceImplTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private ProjectService projectService;

    private EntityManager em;

    private EmbeddedDatabase db;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db_scripts/sprints.sql")
                .build();
    }

    @Test
    public void getProjectManager() throws Exception {
        User projectManager = projectService.getProjectManager(1);
        System.out.println(projectManager);
    }

    @Test
    public void getProjectSprintsCount() throws Exception {
        Long projectSprintsCount = projectService.getProjectSprintsCount(3);
        System.out.println(projectSprintsCount);
    }
}