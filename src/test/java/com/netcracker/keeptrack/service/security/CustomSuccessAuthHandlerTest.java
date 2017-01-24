package com.netcracker.keeptrack.service.security;

import com.netcracker.keeptrack.web.BaseWebTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-security.xml",
})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("hsql")
public class CustomSuccessAuthHandlerTest {

    @Test
    public void onAuthenticationSuccess() throws Exception {
        AuthenticationSuccessHandler successHandler = mock(AuthenticationSuccessHandler.class);
        Authentication authentication = new TestingAuthenticationToken("admin", "qwerty",
                "ADMIN");
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        successHandler.onAuthenticationSuccess(request, response, authentication);
        Assert.assertNotNull(successHandler);
    }
}