package com.netcracker.keeptrack.service.security;

import com.netcracker.keeptrack.model.Request;
import com.netcracker.keeptrack.model.RequestStatus;
import com.netcracker.keeptrack.repository.UserRepository;
import com.netcracker.keeptrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Implementation of {@link AuthenticationSuccessHandler}.
 * Adds data about the user after authorization.
 *
 * @see AuthenticationSuccessHandler
 */
public class CustomSuccessAuthHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Encapsulates the redirection logic for all classes in the framework
     * which perform redirects.
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * If the user is successfully authenticated, get his data from the database and
     * put them to the session. After that redirects to the targetUrl.
     *
     * @param request provide request information for HTTP servlets
     * @param response provide HTTP-specific functionality in sending a response
     * @param authentication represents the token for an authentication request or for an
     *                       authenticated principal
     */
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession(true);

        User credentials = (User) authentication.getPrincipal();
        String username = credentials.getUsername();
        com.netcracker.keeptrack.model.User user = userRepository.getUserByUsername(username);

        List<Request> userOpenedRequests = userService.getUserRequestsByStatus(user, RequestStatus.OPENED);

        session.setAttribute("user", user);
        session.setAttribute("userOpenedRequests", userOpenedRequests);

        if (response.isCommitted()) {
            return;
        }

        String targetUrl = "/home";

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
