package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Task;
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
public class TaskRepositoryTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private TaskRepository taskRepository;

    private EntityManager em;

    private EmbeddedDatabase db;

    private String taskName;

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

        taskName = "Task 1";
    }

    @Test
    public void getTaskByName() throws Exception {
        final Task RESULT_TASK = taskRepository.getTaskByName(taskName);
        Assert.assertNotNull(RESULT_TASK);
        Assert.assertEquals(taskName, RESULT_TASK.getName());
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}