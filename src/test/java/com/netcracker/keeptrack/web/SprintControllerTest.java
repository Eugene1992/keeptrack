package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.web.dto.SprintDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class SprintControllerTest extends BaseWebTestConfig {

    @Autowired
    private SprintController sprintController;

    @Test
    public void constructSprintTest() throws Exception {
        final SprintDTO RESULT = sprintController.constructSprint();
        Assert.assertNotNull(RESULT);
    }

    @Test
    public void getAllSprintsTest() throws Exception {
        mockMvc.perform(get("/sprints"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("sprints"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/sprints.jsp"))
                .andExpect(model().attribute("sprintsList", hasSize(8)))
                .andExpect(model().attribute("projects", hasSize(4)));
    }

    @Test
    public void addNewSprintTest() throws Exception {
        mockMvc.perform(post("/sprints/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "New Sprint")
                .param("startDate", "2017-02-21")
                .param("endDate", "2018-02-21")
                .param("description", "Some description")
                .param("status", "CREATED")
                .param("projectId", "1")
        )
                .andExpect(view().name("redirect:/sprints"));
    }

    @Test
    public void addNewSprintValidationErrorTest() throws Exception {
        mockMvc.perform(post("/sprints/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "New Sprint")
        )
                .andExpect(view().name("new-sprint"))
                .andExpect(model().attributeExists("projects"));
    }


    @Test
    public void updateSprintTestPathVariable() throws Exception {
        mockMvc.perform(post("/sprints/update/{id}", 1))
                .andExpect(view().name("upd-sprint"))
                .andExpect(model().attributeExists("updSprint"))
                .andExpect(model().attributeExists("projects"));
    }

    @Test
    public void updateSprintTest() throws Exception {
        mockMvc.perform(post("/sprints/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "New Sprint")
                .param("projectId", "1")
                .param("status", "CREATED")
                .param("startDate", "2017-02-21")
                .param("endDate", "2018-02-21")
                .param("description", "Some description")
        )
                .andExpect(view().name("redirect:/sprints"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void updateSprintValidationTest() throws Exception {
        mockMvc.perform(post("/sprints/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "New Sprint")
        )
                .andExpect(view().name("upd-sprint"))
                .andExpect(model().attributeExists("updSprint"))
                .andExpect(model().attributeExists("projects"));
    }

    @Test
    public void deleteSprintTest() throws Exception {
        mockMvc.perform(post("/sprints/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
        )
                .andExpect(view().name("redirect:/sprints"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void getSprintProfileTest() throws Exception {
        mockMvc.perform(get("/sprint/{name}", "Sprint 1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(view().name("sprint-profile"))
                .andExpect(model().attributeExists("sprint"));
    }

    @Test
    public void addSprintToProjectTest() throws Exception {
        mockMvc.perform(post("/project/sprints/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "New Sprint")
                .param("projectId", "1")
                .param("status", "CREATED")
                .param("startDate", "2017-02-21")
                .param("endDate", "2018-02-21")
                .param("description", "Some description")
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void addSprintToProjectValidationErrorTest() throws Exception {
        mockMvc.perform(post("/project/sprints/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Sprint 1")
                .param("projectId", "1")
                .param("status", "CREATED")
                .param("startDate", "2017-02-21")
                .param("endDate", "2018-02-21")
                .param("description", "Some description")
        )
                .andExpect(view().name("project-add-sprint"));
    }

    @Test
    public void deleteSprintFromProjectTest() throws Exception {
        mockMvc.perform(post("/project/sprints/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("sprintId", "1")
                .param("projectName", "Minerva")
        )
                .andExpect(status().is3xxRedirection());
    }
}