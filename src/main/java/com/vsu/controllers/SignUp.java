package com.vsu.controllers;

import com.vsu.entities.User;
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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/sign-up")
public class SignUp extends HttpServlet {
    public static final String JSP_PATH = "/jsp/";
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
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        RequestDispatcher dispatcher = null;
        try {
            User user =  userService.getByLoginAndPassword(login, password);
            req.setAttribute("user", user);
            session.setAttribute("user", user);
            dispatcher = req.getRequestDispatcher(JSP_PATH + "user-page.jsp");
        } catch (ValidationException e) {
            dispatcher = req.getRequestDispatcher(JSP_PATH +"user-login.jsp");
            req.setAttribute("error", e.getMessage());
        }
        dispatcher.forward(req, resp);
    }
}
