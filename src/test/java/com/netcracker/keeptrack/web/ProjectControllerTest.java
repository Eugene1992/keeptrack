package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.web.dto.ProjectDTO;
import com.netcracker.keeptrack.web.dto.SprintDTO;
import com.netcracker.keeptrack.web.dto.TaskDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.security.Principal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProjectControllerTest extends BaseWebTestConfig {

    @Autowired
    private ProjectController projectController;

    @Test
    public void constructProjectTest() throws Exception {
        final ProjectDTO RESULT = projectController.constructProject();
        Assert.assertNotNull(RESULT);
    }

    @Test
    public void constructSprintTest() throws Exception {
        final SprintDTO RESULT = projectController.constructSprint();
        Assert.assertNotNull(RESULT);
    }

    @Test
    public void constructTaskTest() throws Exception {
        final TaskDTO RESULT = projectController.constructTask();
        Assert.assertNotNull(RESULT);
    }

    @Test
    public void getAllProjectsTest() throws Exception {
        mockMvc.perform(get("/projects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("projects"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/projects.jsp"))
                .andExpect(model().attribute("freeManagers", hasSize(5)))
                .andExpect(model().attribute("freeEmployees", hasSize(5)))
                .andExpect(model().attribute("projects", hasSize(4)));
    }

    @Test
    public void addNewProjectTest() throws Exception {
        mockMvc.perform(post("/projects/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Google")
                .param("managerId", "1")
                .param("status", "CREATED")
                .param("startDate", "2017-02-21")
                .param("endDate", "2018-02-21")
                .param("description", "Some description")
                .param("employees", "1", "2")
        )
                .andExpect(view().name("redirect:/project/Google"));
    }

    @Test
    public void addNewProjectValidationErrorTest() throws Exception {
        mockMvc.perform(post("/projects/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Minerva")
                .param("managerId", "1")
                .param("status", "")
                .param("startDate", "")
                .param("endDate", "")
                .param("description", "")
                .param("employees", "1", "2")
        )
                .andExpect(view().name("new-project"))
                .andExpect(model().attributeExists("freeEmployees"))
                .andExpect(model().attributeExists("freeManagers"));
    }

    @Test
    public void updateProjectTest() throws Exception {
        mockMvc.perform(post("/projects/update/{id}", 1))
                .andExpect(view().name("upd-project"))
                .andExpect(model().attributeExists("updProject"))
                .andExpect(model().attributeExists("allEmployees"))
                .andExpect(model().attributeExists("allManagers"));
    }

    @Test
    public void updateProjectValidationErrorTest() throws Exception {
        mockMvc.perform(post("/projects/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "Minerva")
                .param("managerId", "1")
                .param("status", "")
                .param("startDate", "")
                .param("endDate", "")
                .param("description", "")
                .param("employees", "1", "2")
        )
                .andExpect(view().name("upd-project"))
                .andExpect(model().attributeExists("allEmployees"))
                .andExpect(model().attributeExists("allManagers"));
    }

    @Test
    public void deleteProjectTest() throws Exception {
        mockMvc.perform(post("/projects/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
        )
                .andExpect(view().name("redirect:/projects"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void projectTest() throws Exception {
        mockMvc.perform(get("/project/{name}", "Minerva"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("project"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/project-profile.jsp"))
                .andExpect(model().attributeExists("freeEmployees"))
                .andExpect(model().attributeExists("currentProject"))
                .andExpect(model().attributeExists("projectManager"))
                .andExpect(model().attributeExists("totalProjectEmployees"))
                .andExpect(model().attributeExists("totalProjectSprints"));
    }

    @Test
    public void userProjectTest() throws Exception {
        mockMvc.perform(get("/project").principal(() -> "employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user-project"));
    }

    @Test
    public void deleteEmployeeFromProjectTest() throws Exception {
        mockMvc.perform(post("/project/employees/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("employeeId", "1")
                .param("projectName", "Minerva")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void addEmployeeToProjectTest() throws Exception {
        mockMvc.perform(post("/project/employees/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("employeeId", "17")
                .param("projectName", "Minerva")
        )
                .andExpect(status().is3xxRedirection());
    }
}