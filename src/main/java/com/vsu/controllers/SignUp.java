package com.vsu.controllers;

import com.vsu.exception.ValidationException;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.UserRepository;
import com.vsu.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sign-up")
public class SignUp extends HttpServlet {
    public static final String JSP_PATH = "/WEB-INF/jsp/";
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = new UserService(new UserRepository(new ConnectionFactory()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        RequestDispatcher dispatcher = null;
        try {
            req.setAttribute("user", userService.getByLoginAndPassword(login, password));
            dispatcher = req.getRequestDispatcher(JSP_PATH + "user-page.jsp");
        } catch (ValidationException e) {
            dispatcher = req.getRequestDispatcher("user-login.jsp");
            req.setAttribute("error", e.getMessage());
        }
        dispatcher.forward(req, resp);
    }
}
