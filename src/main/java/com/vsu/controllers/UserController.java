package com.vsu.controllers;

import com.vsu.entities.User;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class UserController extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userRepository = new UserRepository(new ConnectionFactory());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(req, resp);
                break;
            case "/check":
                try {
                    checkUser(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/insert":
                insertUser(req, resp);
                break;
            case "/edit":
                showEditForm(req, resp);
                break;
            case "/update":
                updateUser(req, resp);
                break;
            default:
                showUserPage(req, resp);
                break;
        }
    }

    private void checkUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userRepository.selectByEmail(login);
        if (user == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-login.jsp");
            req.setAttribute("error", "User with this login does not exist");
            dispatcher.forward(req, resp);
            return;
        }
        if (user.getPassword().equals(password)) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-page.jsp");
            req.setAttribute("user", user);
            dispatcher.forward(req, resp);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-login.jsp");
            req.setAttribute("error", "Wrong password");
            dispatcher.forward(req, resp);
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        User user = new User(surname, name, birthday, email, phone, password);
        try {
            userRepository.insert(user);
            user = userRepository.selectByEmail(email);
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-page.jsp");
        req.setAttribute("user", user);
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        User existingUser;
        try {
            existingUser = userRepository.selectById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
            req.setAttribute("user", existingUser);
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        User user = new User(id, surname, name, birthday, email, phone, password);
        try {
            userRepository.updateByID(user);
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("user-page.jsp");
        req.setAttribute("user", user);
        dispatcher.forward(req, resp);
    }

    private void showUserPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-page.jsp");
        dispatcher.forward(req, resp);
    }
}
