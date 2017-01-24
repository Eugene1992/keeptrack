package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.BaseTestConfig;
import com.netcracker.keeptrack.model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class TaskRepositoryTest extends BaseTestConfig {

    @Autowired
    private TaskRepository taskRepository;

    private String taskName;

    @Before
    public void setUp() throws Exception {
        taskName = "Task 1";
    }

    @Test
    public void getTaskByNameTest() throws Exception {
        final Task RESULT_TASK = taskRepository.getTaskByName(taskName);
        Assert.assertNotNull(RESULT_TASK);
        Assert.assertEquals(taskName, RESULT_TASK.getName());
    }

    @Test
    public void getTotalTasksCount() throws Exception {
        final Long EXPECTED_COUNT = 18L;
        final Long RESULT_COUNT = taskRepository.getTotalTasksCount();
        Assert.assertNotNull(RESULT_COUNT);
        Assert.assertEquals(RESULT_COUNT, EXPECTED_COUNT);
    }

    @Test
    public void getLatestTasks() throws Exception {
        final Integer EXPECTED_COUNT = 10;
        final List<Task> LATEST_TASKS = taskRepository.getLatestTasks(new PageRequest(0, EXPECTED_COUNT));
        final Integer RESULT_COUNT = LATEST_TASKS.size();
        Assert.assertNotNull(RESULT_COUNT);
        Assert.assertEquals(RESULT_COUNT, EXPECTED_COUNT);
    }
}