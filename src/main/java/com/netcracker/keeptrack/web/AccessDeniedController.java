package com.netcracker.keeptrack.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Custom access denied page controller.
 */
@Controller
public class AccessDeniedController {

    /**
     * Redirects to the page when the user has requested a resource without permission.
     * @return access denied tiles page definition
     */
    @RequestMapping(value = "/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
