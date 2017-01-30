package com.netcracker.keeptrack.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReportControllerTest extends BaseWebTestConfig {

    @Test
    public void reports() throws Exception {
        mockMvc.perform(get("/reports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reports"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/reports.jsp"))
                .andExpect(model().attributeExists("projects"))
                .andExpect(model().attributeExists("employees"));
    }
}