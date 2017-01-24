package com.netcracker.keeptrack.service.validators;

import com.netcracker.keeptrack.web.BaseWebTestConfig;
import com.netcracker.keeptrack.web.dto.ProjectDTO;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import com.netcracker.keeptrack.web.dto.TaskDTO;
import com.netcracker.keeptrack.web.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorsSupportsTest extends BaseWebTestConfig {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ProjectValidator projectValidator;

    @Autowired
    private TaskValidator taskValidator;

    @Autowired
    private SprintValidator sprintValidator;

    @Test
    public void userValidatorSupportsTest() throws Exception {
        boolean RESULT = userValidator.supports(UserDTO.class);
        Assert.assertTrue(RESULT);
    }

    @Test
    public void projectValidatorSupportsTest() throws Exception {
        boolean RESULT = projectValidator.supports(ProjectDTO.class);
        Assert.assertTrue(RESULT);
    }

    @Test
    public void taskValidatorSupportsTest() throws Exception {
        boolean RESULT = taskValidator.supports(TaskDTO.class);
        Assert.assertTrue(RESULT);
    }

    @Test
    public void sprintValidatorSupportsTest() throws Exception {
        boolean RESULT = sprintValidator.supports(SprintDTO.class);
        Assert.assertTrue(RESULT);
    }
}