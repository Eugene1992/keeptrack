package com.netcracker.keeptrack.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccessDeniedControllerTest extends BaseWebTestConfig {

    @Test
    public void accessDeniedTest() throws Exception {
        mockMvc.perform(get("/access-denied"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("access-denied"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/access-denied.jsp"));
    }
}