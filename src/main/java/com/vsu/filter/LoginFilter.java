package com.vsu.filter;

import com.vsu.entities.User;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.UserRepository;
import com.vsu.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/user/*")
public class LoginFilter implements Filter {

    private static final String JSP_PATH = "/jsp/";

    @Override
    public void doFilter (ServletRequest request, ServletResponse response,
                          FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            httpServletRequest.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JSP_PATH + "user-page.jsp");
            dispatcher.forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}
