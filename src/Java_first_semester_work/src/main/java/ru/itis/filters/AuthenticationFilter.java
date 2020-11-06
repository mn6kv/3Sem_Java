package ru.itis.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        Boolean isSessionExists = session != null;
        Boolean isAuthenticated = false;
        Boolean isRequestOnOpenPage = request.getRequestURI().equals("/signIn") ||
                request.getRequestURI().equals("/signUp");

        if (isSessionExists)
            isAuthenticated = session.getAttribute("user") != null;

        if (isAuthenticated && !isRequestOnOpenPage || !isAuthenticated && isRequestOnOpenPage) {
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isRequestOnOpenPage) {
            response.sendRedirect("/main");
        } else {
            response.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}
