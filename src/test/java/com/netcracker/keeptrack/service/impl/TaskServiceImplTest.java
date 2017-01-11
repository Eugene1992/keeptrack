package com.netcracker.keeptrack.service.impl;

import com.netcracker.keeptrack.model.Employee;
import com.netcracker.keeptrack.model.Gender;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.repository.TaskRepository;
import com.netcracker.keeptrack.service.TaskService;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
})
@ActiveProfiles("hsql")
public class TaskServiceImplTest {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private TaskService taskService;

    private EntityManager em;

    private Task task;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
        employee = new Employee("Evgeniy", "Deyneka", 10000, "deyneko55@gmail.com", Gender.MALE,
                LocalDate.of(1992, 12, 26), LocalDate.of(2016, 2, 10));
        task = new Task("Task #1", employee, employee, 21, TaskStatus.OPEN, "Some description");
        taskService.addTask(task);
    }

    @Test
    public void addTask() throws Exception {
        final Task RESULT = taskService.getTaskById(task.getId());
        Assert.assertEquals(task, RESULT);
    }

    @Test
    public void deleteTask() throws Exception {
        taskService.deleteTask(task.getId());
        final Task RESULT = taskService.getTaskById(task.getId());
        Assert.assertNull(RESULT);
    }

    @Test
    public void getTaskById() throws Exception {
        final Task RESULT = taskService.getTaskById(task.getId());
        Assert.assertEquals(task, RESULT);
    }

    @Test
    public void editTask() throws Exception {
        Task updatedTask = taskService.getTaskById(task.getId());
        final int NEW_ESTIMATE = 100;
        updatedTask.setEstimate(NEW_ESTIMATE);
        taskService.editTask(updatedTask);
        final int UPD_ESTIMATE = taskService.getTaskById(task.getId()).getEstimate();
        Assert.assertEquals(NEW_ESTIMATE, UPD_ESTIMATE);
    }
}