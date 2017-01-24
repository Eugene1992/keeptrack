package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.web.dto.TaskDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerTest extends BaseWebTestConfig {

    @Autowired
    private TaskController taskController;

    @Test
    public void construct() throws Exception {
        final TaskDTO RESULT = taskController.constructTask();
        Assert.assertNotNull(RESULT);
    }

    @Test
    public void getAllProjects() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("tasks"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/tasks.jsp"))
                .andExpect(model().attribute("tasksList", hasSize(18)))
                .andExpect(model().attribute("allSprints", hasSize(8)))
                .andExpect(model().attribute("allEmployees", hasSize(18)))
                .andExpect(model().attribute("allManagers", hasSize(8)));
    }

    @Test
    public void addNewTask() throws Exception {
        mockMvc.perform(post("/tasks/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "New Task")
                .param("startDate", "2017-02-21")
                .param("endDate", "2018-02-21")
                .param("status", "CREATED")
                .param("description", "Some description")
                .param("estimate", "4")
                .param("creatorId", "1")
                .param("assignerId", "1")
                .param("sprintId", "1")
        )
                .andExpect(view().name("redirect:/tasks"));
    }

    @Test
    public void addNewTaskValidationErrorTest() throws Exception {
        mockMvc.perform(post("/tasks/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "")
                .param("startDate", "2017-02-02")
                .param("endDate", "2017-02-01")
                .param("status", "")
                .param("description", "")
                .param("estimate", "")
                .param("creatorId", "")
                .param("assignerId", "")
                .param("sprintId", "")
        )
                .andExpect(view().name("add-task"))
                .andExpect(model().attributeExists("allSprints"))
                .andExpect(model().attributeExists("allEmployees"))
                .andExpect(model().attributeExists("allManagers"));
    }

    @Test
    public void updateTaskPathVariable() throws Exception {
        mockMvc.perform(post("/tasks/update/{id}", 1))
                .andExpect(view().name("upd-task"))
                .andExpect(model().attributeExists("updTask"))
                .andExpect(model().attributeExists("allSprints"))
                .andExpect(model().attributeExists("allEmployees"))
                .andExpect(model().attributeExists("allManagers"));
    }

    @Test
    public void updateTask() throws Exception {
        mockMvc.perform(post("/tasks/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "New Task")
                .param("startDate", "2017-02-21")
                .param("endDate", "2018-02-21")
                .param("status", "CREATED")
                .param("description", "Some description")
                .param("estimate", "4")
                .param("creatorId", "1")
                .param("assignerId", "1")
                .param("sprintId", "1")
        )
                .andExpect(view().name("redirect:/tasks"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void updateTaskValidationTest() throws Exception {
        mockMvc.perform(post("/tasks/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "")
                .param("startDate", "")
                .param("endDate", "")
                .param("status", "")
                .param("description", "")
                .param("estimate", "")
                .param("creatorId", "")
                .param("assignerId", "")
                .param("sprintId", "")
        )
                .andExpect(view().name("upd-task"))
                .andExpect(model().attributeExists("allSprints"))
                .andExpect(model().attributeExists("allEmployees"))
                .andExpect(model().attributeExists("allManagers"));
    }

    @Test
    public void deleteTask() throws Exception {
        mockMvc.perform(post("/tasks/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
        )
                .andExpect(view().name("redirect:/tasks"))
                .andExpect(status().is3xxRedirection());
    }

    @Test(expected = NestedServletException.class)
    public void deleteErrorTask() throws Exception {
        mockMvc.perform(post("/tasks/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
        );
    }

    @Test
    public void addTaskToSprint() throws Exception {
        mockMvc.perform(post("/project/tasks/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "New Task")
                .param("startDate", "2017-02-21")
                .param("endDate", "2018-02-21")
                .param("status", "CREATED")
                .param("description", "Some description")
                .param("estimate", "4")
                .param("creatorId", "1")
                .param("assignerId", "6")
                .param("sprintId", "1")
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void addTaskToSprintValidationErrorTest() throws Exception {
        mockMvc.perform(post("/project/tasks/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "New Task")
                .param("startDate", "")
                .param("endDate", "")
                .param("status", "CREATED")
                .param("description", "Some description")
                .param("estimate", "4")
                .param("creatorId", "1")
                .param("assignerId", "6")
                .param("sprintId", "1")
        )
                .andExpect(view().name("project-add-task"));
    }

    @Test
    public void deleteTaskFromSprint() throws Exception {
        mockMvc.perform(post("/project/tasks/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("taskId", "1")
                .param("projectName", "Minerva")
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void getTaskProfile() throws Exception {
        mockMvc.perform(get("/task/{name}", "Task 1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(view().name("task-profile"))
                .andExpect(model().attributeExists("task"));
    }

}