package com.netcracker.keeptrack.web;

import com.netcracker.keeptrack.web.dto.UserDTO;
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

public class UserControllerTest extends BaseWebTestConfig {


    @Autowired
    private UserController userController;

    @Test
    public void constructTest() throws Exception {
        final UserDTO RESULT = userController.constructUser();
        Assert.assertNotNull(RESULT);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attribute("allUsers", hasSize(28)))
                .andExpect(model().attribute("allProjects", hasSize(4)));
    }

    @Test
    public void addNewUserTest() throws Exception {
        mockMvc.perform(post("/users/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "New User")
                .param("password", "qwerty")
                .param("projectId", "1")
                .param("role", "EMPLOYEE")
                .param("firstName", "Ivan")
                .param("lastName", "Ivanov")
                .param("salary", "2018")
                .param("email", "ivanov@gmail.com")
                .param("gender", "MALE")
                .param("birthday", "1992-12-26")
                .param("hireDay", "2017-02-12")
        )
                .andExpect(view().name("redirect:/users"));
    }

    @Test
    public void addNewUserValidationErrorTest() throws Exception {
        mockMvc.perform(post("/users/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "admin")
                .param("password", "")
                .param("projectId", "1")
                .param("role", "")
                .param("firstName", "Evgeniy")
                .param("lastName", "Deyneka")
                .param("salary", "")
                .param("email", "deyneko55@gmail.com")
                .param("gender", "")
                .param("birthday", "")
                .param("hireDay", "")
        )
                .andExpect(view().name("new-user"))
                .andExpect(model().attributeExists("allProjects"));
    }

    @Test
    public void updateUserTestPathVariable() throws Exception {
        mockMvc.perform(post("/users/update/{id}", 1))
                .andExpect(view().name("upd-user"))
                .andExpect(model().attributeExists("allProjects"))
                .andExpect(model().attributeExists("updUser"));
    }

    @Test
    public void updateUserTest() throws Exception {
        mockMvc.perform(post("/users/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("username", "New User")
                .param("password", "qwerty")
                .param("projectId", "1")
                .param("role", "EMPLOYEE")
                .param("firstName", "Ivan")
                .param("lastName", "Ivanov")
                .param("salary", "2018")
                .param("email", "ivanov@gmail.com")
                .param("gender", "MALE")
                .param("birthday", "1992-12-26")
                .param("hireDay", "2017-02-12")
        )
                .andExpect(view().name("redirect:/users"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void updateUserValidationTest() throws Exception {
        mockMvc.perform(post("/users/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("username", "Evgeniy")
                .param("password", "Too much password length")
                .param("projectId", "1")
                .param("role", "")
                .param("firstName", "n")
                .param("lastName", "Deyneka")
                .param("salary", "-1200")
                .param("email", "deyneko55@gmail.com")
                .param("gender", "")
                .param("birthday", "")
                .param("hireDay", "")
        )
                .andExpect(model().attributeExists("allProjects"))
                .andExpect(view().name("upd-user"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(post("/users/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
        )
                .andExpect(view().name("redirect:/users"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void getUserProfileTest() throws Exception {
        mockMvc.perform(get("/user/{name}", "admin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(view().name("user-profile"))
                .andExpect(model().attributeExists("user"));
    }
}