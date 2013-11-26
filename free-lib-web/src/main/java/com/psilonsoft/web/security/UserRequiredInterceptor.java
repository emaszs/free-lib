package com.psilonsoft.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.psilonsoft.web.WebApplicationConfig;

/**
 * Ensures that not logged in user cannot access restricted pages.
 * 
 * This is a simple Spring {@link HandlerInterceptor} that is executed on every request.
 * We use it, to extract user information from session, and check if user can access given page.
 * 
 * Paths, which should be filtered are registerd within {@link WebApplicationConfig}. If this
 * handler executes, we assume that path must be secured.
 * 
 * 
 */
@Component
public class UserRequiredInterceptor implements HandlerInterceptor {

    private final static String LOGIN_PATH = "login";

    /**
     * Our session scoped bean, which holds currently logged in user in session.
     */
    @Autowired
    private CurrentUser currentUser;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        if (!currentUser.isLoggedIn()) {
            response.sendRedirect("/" + LOGIN_PATH);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object arg2, final Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object arg2, final ModelAndView arg3)
            throws Exception {
    }

}
