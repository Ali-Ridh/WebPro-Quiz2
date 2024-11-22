package controller;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/school",
                "root",
                "password"
            );
            userDAO = new UserDAOImpl(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action == null) {
                action = "list";
            }
            switch (action) {
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                default:
                    listUsers(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("user-list.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        User existingUser = userDAO.getUserById(userId);
        req.setAttribute("user", existingUser);
        req.getRequestDispatcher("user-form.jsp").forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(userId);
        resp.sendRedirect("user");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "insert";
        }
        try {
            switch (action) {
                case "update":
                    updateUser(req, resp);
                    break;
                default:
                    insertUser(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String role = req.getParameter("role");

        User newUser = new User(0, username, password, fullName, role, null);
        userDAO.addUser(newUser);
        resp.sendRedirect("user");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String role = req.getParameter("role");

        User updatedUser = new User(userId, username, password, fullName, role, null);
        userDAO.updateUser(updatedUser);
        resp.sendRedirect("user");
    }
}
