package com.netcracker.keeptrack.service;

import com.netcracker.keeptrack.BaseTestConfig;
import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import com.netcracker.keeptrack.web.dto.ProjectDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ProjectServiceTest extends BaseTestConfig {

    @Autowired
    private ProjectService projectService;

    private ProjectDTO project;

    @Before
    public void setUp() throws Exception {
        project = new ProjectDTO();
        project.setName("Cerebra");
        project.setDescription("Some description");
        project.setEndDate("2017-02-10");
        project.setStartDate("2017-04-12");
        project.setManagerId("2");
        project.setEmployees(new HashSet<>(Arrays.asList("20", "21")));
    }

    @Test
    public void addProjectTest() throws Exception {
        projectService.addProject(project);
        final Project SAVED_PROJECT = projectService.getProjectByName(project.getName());
        final Integer PROJECT_EMPLOYEES_SIZE = 2;
        final Integer SAVED_PROJECT_SIZE = SAVED_PROJECT.getUsers().size();
        Assert.assertNotNull(SAVED_PROJECT);
        Assert.assertEquals(SAVED_PROJECT_SIZE, PROJECT_EMPLOYEES_SIZE);
        Assert.assertEquals(SAVED_PROJECT.getName(), project.getName());
    }

    @Test
    public void deleteProjectTest() throws Exception {
        final Integer DELETED_PROJECT_ID = 1;
        final Project PROJECT_BEFORE_DELETING = projectService.getProjectById(DELETED_PROJECT_ID);
        Assert.assertNotNull(PROJECT_BEFORE_DELETING);
        projectService.deleteProject(DELETED_PROJECT_ID);
        final Project PROJECT_AFTER_DELETING = projectService.getProjectById(DELETED_PROJECT_ID);
        Assert.assertNull(PROJECT_AFTER_DELETING);
    }

    @Test
    public void getProjectByIdTest() throws Exception {
        final Integer PROJECT_ID = 2;
        final String PROJECT_NAME = "Minerva";
        final Project RESULT_PROJECT = projectService.getProjectById(PROJECT_ID);
        Assert.assertNotNull(RESULT_PROJECT);
        Assert.assertEquals(PROJECT_NAME, RESULT_PROJECT.getName());
    }

    @Test
    public void getProjectByNameTest() throws Exception {
        final String PROJECT_NAME = "Minerva";
        final Project RESULT_PROJECT = projectService.getProjectByName(PROJECT_NAME);
        Assert.assertNotNull(RESULT_PROJECT);
        Assert.assertEquals(PROJECT_NAME, RESULT_PROJECT.getName());
    }

    @Test
    public void updateProject() throws Exception {
        final Integer PROJECT_ID = 1;
        final Project PROJECT = projectService.getProjectById(PROJECT_ID);
        final String PROJECT_NAME = PROJECT.getName();
        final String NEW_PROJECT_NAME = PROJECT_NAME + " NEW";
        PROJECT.setName(NEW_PROJECT_NAME);
        projectService.updateProject(new ProjectDTO(PROJECT));
        final Project UPD_PROJECT = projectService.getProjectById(PROJECT_ID);
        final String UPD_PROJECT_NAME = UPD_PROJECT.getName();
        Assert.assertNotNull(UPD_PROJECT);
        Assert.assertEquals(PROJECT.getName(), UPD_PROJECT_NAME);
    }

    @Test
    public void getAllProjectsTest() throws Exception {
        final Integer EXPECTED_SIZE = 4;
        final List<Project> ALL_PROJECTS = projectService.getAllProjects();
        final Integer RESULT_SIZE = ALL_PROJECTS.size();
        Assert.assertNotNull(RESULT_SIZE);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getProjectManagerTest() throws Exception {
        final Integer PROJECT_ID = 1;
        final User PM = projectService.getProjectManager(PROJECT_ID);
        Assert.assertNotNull(PM);
        Assert.assertEquals(PM.getFirstName(), "Gloria");
        Assert.assertEquals(PM.getLastName(), "Campbell");
    }

    @Test
    public void getProjectEmployeesCountTest() throws Exception {
        final Integer PROJECT_ID = 1;
        final Long EXPECTED_SIZE = 2L;
        Long RESULT_SIZE = projectService.getProjectEmployeesCount(PROJECT_ID);
        Assert.assertNotNull(RESULT_SIZE);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void getProjectSprintsCountTest() throws Exception {
        final Integer PROJECT_ID = 1;
        final Long EXPECTED_SIZE = 2L;
        Long RESULT_SIZE = projectService.getProjectSprintsCount(PROJECT_ID);
        Assert.assertNotNull(RESULT_SIZE);
        Assert.assertEquals(EXPECTED_SIZE, RESULT_SIZE);
    }

    @Test
    public void checkProjectNameTest() throws Exception {
        final String EXISTING_PROJECT_NAME = "Minerva";
        final boolean RESULT = projectService.checkProjectName(EXISTING_PROJECT_NAME);
        Assert.assertTrue(RESULT);
    }

    @Test
    public void checkNotExistProjectNameTest() throws Exception {
        final String NOT_EXISTING_PROJECT_NAME = "XXX";
        final boolean RESULT = projectService.checkProjectName(NOT_EXISTING_PROJECT_NAME);
        Assert.assertFalse(RESULT);
    }
}