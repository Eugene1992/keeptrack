package com.netcracker.keeptrack.service.security;

import com.netcracker.keeptrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Custom success login filter.
 */
public class CustomSuccessAuthHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication) throws IOException, ServletException {

        super.onAuthenticationSuccess(request, response, authentication);

        HttpSession session = request.getSession(true);

        User credentials = (User) authentication.getPrincipal();
        String username = credentials.getUsername();
        com.netcracker.keeptrack.model.User user = userRepository.getUserByUsername(username);

        try {
            session.setAttribute("user", user);
        } catch (Exception e) {
            logger.error("Error in getting User()", e);
        }
    }
}
