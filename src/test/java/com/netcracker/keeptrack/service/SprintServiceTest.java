package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.BaseTestConfig;
import com.netcracker.keeptrack.model.Sprint;
import com.netcracker.keeptrack.model.SprintStatus;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SprintServiceTest extends BaseTestConfig {

    @Autowired
    private SprintService sprintService;

    private SprintDTO sprint;

    @Before
    public void setUp() throws Exception {
        sprint = new SprintDTO();
        sprint.setName("Some sprint");
        sprint.setDescription("Some description");
        sprint.setStartDate("2017-01-18");
        sprint.setEndDate("2017-02-18");
        sprint.setStatus(SprintStatus.CREATED.name());
        sprint.setProjectId("1");
    }

    @Test
    public void addSprintTest() throws Exception {
        sprintService.addSprint(sprint);
        final Sprint SAVED_SPRINT = sprintService.getSprintByName(sprint.getName());
        Assert.assertNotNull(SAVED_SPRINT);
        Assert.assertEquals(sprint.getName(), SAVED_SPRINT.getName());
        Assert.assertEquals(sprint.getDescription(), SAVED_SPRINT.getDescription());
    }

    @Test
    public void deleteSprint() throws Exception {
        final Integer DELETED_SPRINT_ID = 1;
        final Sprint SPRINT_BEFORE_DELETING = sprintService.getSprintById(DELETED_SPRINT_ID);
        Assert.assertNotNull(SPRINT_BEFORE_DELETING);
        sprintService.deleteSprint(DELETED_SPRINT_ID);
        final Sprint SPRINT_AFTER_DELETING = sprintService.getSprintById(DELETED_SPRINT_ID);
        Assert.assertNull(SPRINT_AFTER_DELETING);
    }

    @Test
    public void getSprintById() throws Exception {
        final Integer SPRINT_ID = 3;
        final String SPRINT_NAME = "Sprint 6";
        final Sprint RESULT_SPRINT = sprintService.getSprintById(SPRINT_ID);
        Assert.assertNotNull(RESULT_SPRINT);
        Assert.assertEquals(SPRINT_NAME, RESULT_SPRINT.getName());
    }

    @Test
    public void getSprintByName() throws Exception {
        final String SPRINT_NAME = "Sprint 2";
        final Sprint RESULT_SPRINT = sprintService.getSprintByName(SPRINT_NAME);
        Assert.assertNotNull(RESULT_SPRINT);
        Assert.assertEquals(SPRINT_NAME, RESULT_SPRINT.getName());
    }

    @Test
    public void updateSprint() throws Exception {
        final Sprint SPRINT = sprintService.getSprintById(8);
        final String SPRINT_NAME = SPRINT.getName();
        final String NEW_SPRINT_NAME = SPRINT_NAME + " NEW";
        SPRINT.setName(NEW_SPRINT_NAME);
        sprintService.updateSprint(new SprintDTO(SPRINT));
        final Sprint UPD_SPRINT = sprintService.getSprintById(8);
        final String UPD_SPRINT_NAME = UPD_SPRINT.getName();
        Assert.assertNotNull(UPD_SPRINT);
        Assert.assertEquals(UPD_SPRINT.getName(), UPD_SPRINT_NAME);
    }

    @Test
    public void getAllSprints() throws Exception {
        final Integer EXPECTED_SIZE = 8;
        final List<Sprint> ALL_SPRINTS = sprintService.getAllSprints();
        final Integer RESULT_SIZE = ALL_SPRINTS.size();
        Assert.assertNotNull(ALL_SPRINTS);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void deleteSprintFromProject() throws Exception {
        final Integer SPRINT_ID = 7;
        final Sprint RESULT_SPRINT = sprintService.getSprintById(SPRINT_ID);
        Assert.assertNotNull(RESULT_SPRINT);
        Assert.assertNotNull(RESULT_SPRINT.getProject());
        sprintService.deleteSprintFromProject(SPRINT_ID);
        final Sprint AFTER_DELETE_SPRINT = sprintService.getSprintById(SPRINT_ID);
        Assert.assertNull(AFTER_DELETE_SPRINT.getProject());
    }

    @Test
    public void addSprintToProject() throws Exception {
        sprintService.addSprintToProject(sprint);
        final Sprint RESULT_SPRINT = sprintService.getSprintByName(sprint.getName());
        Assert.assertNotNull(RESULT_SPRINT.getProject());
        Assert.assertEquals(Integer.valueOf(sprint.getProjectId()), RESULT_SPRINT.getProject().getId());
    }

    @Test
    public void checkSprintNameTest() throws Exception {
        final String EXISTING_SPRINT_NAME = "Sprint 1";
        final boolean RESULT = sprintService.checkSprintName(EXISTING_SPRINT_NAME);
        Assert.assertTrue(RESULT);
    }

    @Test
    public void checkNotExistSprintNameTest() throws Exception {
        final String NOT_EXISTING_SPRINT_NAME = "XXX";
        final boolean RESULT = sprintService.checkSprintName(NOT_EXISTING_SPRINT_NAME);
        Assert.assertFalse(RESULT);
    }
}