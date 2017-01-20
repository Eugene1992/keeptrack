package com.netcracker.keeptrack.repository;

import com.netcracker.keeptrack.BaseTestConfig;
import com.netcracker.keeptrack.model.Project;
import com.netcracker.keeptrack.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectRepositoryTest extends BaseTestConfig {

    @Autowired
    private ProjectRepository projectRepository;

    private Integer projectId;

    private String projectName;

    @Before
    public void setUp() throws Exception {
        projectId = 1;
        projectName = "Rivecore";
    }

    @Test
    public void getProjectManagerTest() throws Exception {
        final User PROJECT_MANAGER = projectRepository.getProjectManager(projectId);
        Assert.assertNotNull(PROJECT_MANAGER);
        Assert.assertEquals("Gloria", PROJECT_MANAGER.getFirstName());
        Assert.assertEquals("Campbell", PROJECT_MANAGER.getLastName());
        Assert.assertEquals("gcampbell13@domainmarket.com", PROJECT_MANAGER.getEmail());
    }

    @Test
    public void getProjectEmployeesCountTest() throws Exception {
        final Long EMPLOYEES_COUNT = projectRepository.getProjectEmployeesCount(projectId);
        final Long EXPECTED_COUNT = 2L;
        Assert.assertNotNull(EMPLOYEES_COUNT);
        Assert.assertEquals(EXPECTED_COUNT, EMPLOYEES_COUNT);
    }

    @Test
    public void getProjectSprintsCountTest() throws Exception {
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

    @Test
    public void getTotalProjectsCountTest() throws Exception {
        final Long EXPECTED_COUNT = 4L;
        final Long RESULT_COUNT = projectRepository.getTotalProjectsCount();
        Assert.assertNotNull(RESULT_COUNT);
        Assert.assertEquals(RESULT_COUNT, EXPECTED_COUNT);
    }
}