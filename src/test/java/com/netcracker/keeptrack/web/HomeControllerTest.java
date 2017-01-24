package com.netcracker.keeptrack.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeControllerTest extends BaseWebTestConfig {

    @Test
    public void rootTest() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/login.jsp"));
    }

    @Test
    public void homeTest() throws Exception {
        mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/home.jsp"));
    }
}