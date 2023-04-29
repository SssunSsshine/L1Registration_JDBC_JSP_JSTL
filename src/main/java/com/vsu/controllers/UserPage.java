package com.vsu.controllers;

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

@WebServlet("/user/user-page")
public class UserPage extends HttpServlet {
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
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "user-page.jsp");
        dispatcher.forward(req, resp);
    }
}
