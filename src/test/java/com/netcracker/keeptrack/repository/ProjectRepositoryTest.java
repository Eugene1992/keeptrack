package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
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
public class ProjectRepositoryTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private ProjectRepository projectRepository;

    private EntityManager em;

    private EmbeddedDatabase db;

    private Integer projectId;

    private String projectName;

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

        projectId = 1;
        projectName = "Rivecore";
    }

    @Test
    public void getProjectManager() throws Exception {
        final User PROJECT_MANAGER = projectRepository.getProjectManager(projectId);
        Assert.assertNotNull(PROJECT_MANAGER);
        Assert.assertEquals("Gloria", PROJECT_MANAGER.getFirstName());
        Assert.assertEquals("Campbell", PROJECT_MANAGER.getLastName());
        Assert.assertEquals("gcampbell13@domainmarket.com", PROJECT_MANAGER.getEmail());
    }

    @Test
    public void getProjectEmployeesCount() throws Exception {
        final Long EMPLOYEES_COUNT = projectRepository.getProjectEmployeesCount(projectId);
        final Long EXPECTED_COUNT = 2L;
        Assert.assertNotNull(EMPLOYEES_COUNT);
        Assert.assertEquals(EXPECTED_COUNT, EMPLOYEES_COUNT);
    }

    @Test
    public void getProjectSprintsCount() throws Exception {
        final Long SPRINTS_COUNT = projectRepository.getProjectSprintsCount(projectId);
        final Long EXPECTED_COUNT = 2L;
        Assert.assertNotNull(SPRINTS_COUNT);
        Assert.assertEquals(EXPECTED_COUNT, SPRINTS_COUNT);
    }

    @Test
    public void getProjectByName() throws Exception {
        final Project RESULT_PROJECT = projectRepository.getProjectByName(projectName);
        Assert.assertNotNull(RESULT_PROJECT);
        Assert.assertEquals(projectName, RESULT_PROJECT.getName());
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}