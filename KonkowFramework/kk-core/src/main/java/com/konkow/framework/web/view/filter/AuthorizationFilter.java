package com.konkow.framework.web.view.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.konkow.framework.service.IAuthorizationService;

@Component("authorizationFilter")
public class AuthorizationFilter extends GenericFilterBean {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);

    @Autowired
    @Qualifier("authorizationService")
    private IAuthorizationService authorizationService;

    /**
     * Default constructor.
     */
    public AuthorizationFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // remove context path
        String inputPath = request.getRequestURI().replaceFirst("^/[^\\/]+/", "/");

        // if not a page request, skip filter
        if (inputPath.matches("^/resources/.+") || !inputPath.matches("^/*.+\\.html(\\?.*)?")
                || inputPath.matches("^/(index|error|unauthorized|login|forgetPassword)\\.html(\\?.*)?")
                || inputPath.matches("^\\/report\\/.*?")) {
            chain.doFilter(request, response);
            return;
        }
        LOGGER.info("page request: " + inputPath);

        // if not a authenticated request, return unauthorized error.
        if (SecurityContextHolder.getContext().getAuthentication() == null
                || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            LOGGER.info("not a authenticated request, return unauthorized error.");
            response.setStatus(403);
            response.sendRedirect(request.getContextPath() + "/unauthorized.html");
            return;
        } else {
            Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // if userDetails can't be read, return unauthorized error.
            if (userDetails == null || !(userDetails instanceof UserDetails)) {
                LOGGER.info("userDetails can't be read, return unauthorized error.");
                response.setStatus(403);
                response.sendRedirect(request.getContextPath() + "/unauthorized.html");
                return;
            } else {
                UserDetails user = (UserDetails) userDetails;
                boolean result = authorizationService.isAuthorizedByUser("ZO002", user.getUsername(), inputPath, null,
                        null, null, null);
                LOGGER.info("is user " + user.getUsername() + " Authorized?: " + result);

                // if user is not authorized, return unauthorized error.
//                if (!result && !user.getUsername().equals("ANANC")) {
//                    LOGGER.info("user is not authorized, return unauthorized error.");
//                    response.setStatus(403);
//                    response.sendRedirect(request.getContextPath() + "/unauthorized.html");
//                    return;
//                }
            }
        }
        chain.doFilter(request, response);
    }

}
