package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.BaseTestConfig;
import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.Task;
import com.netcracker.keeptrack.model.TaskStatus;
import com.netcracker.keeptrack.web.dto.TaskDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskServiceTest extends BaseTestConfig {

    @Autowired
    private TaskService taskService;

    private TaskDTO task;

    @Before
    public void setUp() throws Exception {
        task = new TaskDTO();
        task.setName("Some task");
        task.setStatus(TaskStatus.OPEN.name());
        task.setDescription("Some description");
        task.setStartDate("2017-01-21");
        task.setEndDate("2017-02-21");
        task.setEstimate("4");
        task.setSprintId("1");
        task.setCreatorId("22");
        task.setAssignerId("5");
    }

    /*@Test
    public void addTask() throws Exception {
        taskService.addTask(task);
        final Task SAVED_TASK = taskService.getTaskByName(task.getName());
        Assert.assertNotNull(SAVED_TASK);
        Assert.assertEquals(task.getName(), SAVED_TASK.getName());
        Assert.assertEquals(task.getDescription(), SAVED_TASK.getDescription());
    }*/

    @Test
    public void deleteTask() throws Exception {
        final Integer DELETED_TASK_ID = 1;
        final Task TASK_BEFORE_DELETING = taskService.getTaskById(DELETED_TASK_ID);
        Assert.assertNotNull(TASK_BEFORE_DELETING);
        taskService.deleteTask(DELETED_TASK_ID);
        final Task TASK_AFTER_DELETING = taskService.getTaskById(DELETED_TASK_ID);
        Assert.assertNull(TASK_AFTER_DELETING);
    }

    @Test
    public void getTaskById() throws Exception {
        final Integer TASK_ID = 3;
        final String TASK_NAME = "Task 3";
        final Task RESULT_TASK = taskService.getTaskById(TASK_ID);
        Assert.assertNotNull(RESULT_TASK);
        Assert.assertEquals(TASK_NAME, RESULT_TASK.getName());
    }

    @Test
    public void getTaskByName() throws Exception {
        final String TASK_NAME = "Task 2";
        final Task RESULT_TASK = taskService.getTaskByName(TASK_NAME);
        Assert.assertNotNull(RESULT_TASK);
        Assert.assertEquals(TASK_NAME, RESULT_TASK.getName());
    }

    @Test
    public void updateTask() throws Exception {

    }

    @Test
    public void getAllTasks() throws Exception {
        final Integer EXPECTED_SIZE = 18;
        final List<Task> ALL_TASKS = taskService.getAllTasks();
        final Integer RESULT_SIZE = ALL_TASKS.size();
        Assert.assertNotNull(ALL_TASKS);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void deleteTaskFromSprint() throws Exception {
        final Integer TASK_ID = 7;
        final Task RESULT_TASK = taskService.getTaskById(TASK_ID);
        Assert.assertNotNull(RESULT_TASK);
        Assert.assertNotNull(RESULT_TASK.getSprint());
        taskService.deleteTaskFromSprint(TASK_ID);
        final Task AFTER_DELETE_TASK = taskService.getTaskById(TASK_ID);
        Assert.assertNull(AFTER_DELETE_TASK.getSprint());
    }
}