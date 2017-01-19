package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskRepositoryTest extends BaseTestConfig {

    @Autowired
    private TaskRepository taskRepository;

    private String taskName;

    @Before
    public void setUp() throws Exception {
        taskName = "Task 1";
    }

    @Test
    public void getTaskByName() throws Exception {
        final Task RESULT_TASK = taskRepository.getTaskByName(taskName);
        Assert.assertNotNull(RESULT_TASK);
        Assert.assertEquals(taskName, RESULT_TASK.getName());
    }
}