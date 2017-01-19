package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.model.Sprint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SprintRepositoryTest extends BaseTestConfig {

    @Autowired
    private SprintRepository sprintRepository;

    private String sprintName;

    @Before
    public void setUp() throws Exception {
        sprintName = "Sprint 1";
    }

    @Test
    public void getSprintByName() throws Exception {
        final Sprint RESULT_SPRINT = sprintRepository.getSprintByName(sprintName);
        Assert.assertNotNull(RESULT_SPRINT);
        Assert.assertEquals(sprintName, RESULT_SPRINT.getName());
    }
}