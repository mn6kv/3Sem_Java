package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/signIn", "/signUp"})
public class SignFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();

        Boolean isSessionExists = httpSession != null;
        Boolean isAuthenticated = false;

        if (isSessionExists)
            isAuthenticated = httpSession.getAttribute("user") != null;

        if (isAuthenticated)
            response.sendRedirect("/main");
        else filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
