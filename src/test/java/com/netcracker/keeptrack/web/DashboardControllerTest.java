package com.netcracker.keeptrack.web;

import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DashboardControllerTest extends BaseWebTestConfig {

    @Test
    public void employeesTest() throws Exception {
        mockMvc.perform(get("/dashboards"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("dashboards"))
                .andExpect(forwardedUrl("/WEB-INF/views/template/dashboards.jsp"))
                .andExpect(model().attribute("totalCustomers", is(1L)))
                .andExpect(model().attribute("totalProjects", is(4L)))
                .andExpect(model().attribute("totalEmployees", is(18L)))
                .andExpect(model().attribute("totalTasks", is(18L)))
                .andExpect(model().attribute("latestTasks", hasSize(10)));
    }
}