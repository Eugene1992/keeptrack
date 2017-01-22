package com.netcracker.keeptrack.service.security;

import com.netcracker.keeptrack.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Custom success login filter.
 */
public class CustomSuccessAuthHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication) throws IOException, ServletException {

        String targetUrl = "/home";

        HttpSession session = request.getSession(true);

        User credentials = (User) authentication.getPrincipal();
        String username = credentials.getUsername();
        com.netcracker.keeptrack.model.User user = userRepository.getUserByUsername(username);

        session.setAttribute("user", user);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
