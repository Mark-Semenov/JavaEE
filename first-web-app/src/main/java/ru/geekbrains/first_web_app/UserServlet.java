package ru.geekbrains.first_web_app;

import ru.geekbrains.first_web_app.persist.Repository;
import ru.geekbrains.first_web_app.persist.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user", urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private Repository userRepo;


    @Override
    public void init() throws ServletException {
        this.userRepo = (Repository) getServletContext().getAttribute("userRepo");
        if (userRepo == null) {
            throw new ServletException("Репозиторий не инициализирован");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo() == null || request.getPathInfo().equals("/user")) {
            request.setAttribute("users", userRepo.getAllUsers());
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        } else if (request.getPathInfo().equals("/edit")) {
            Long id;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException ex) {
                response.setStatus(400);
                return;
            }
            User user = userRepo.getUserById(id);
            if (user == null) {
                response.setStatus(404);
                return;
            }
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(request, response);
        } else if (request.getPathInfo().equals("/delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            userRepo.deleteUser(id);
            response.sendRedirect(getServletContext().getContextPath() + "/user");
        } else if (request.getPathInfo().equals("/add")) {
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;

        try {
            if (request.getParameter("id").equals("")) {
                id = null;
            } else id = Long.parseLong(request.getParameter("id"));

            userRepo.addToRepo(new User(id,
                    request.getParameter("name"),
                    request.getParameter("surname"),
                    Integer.parseInt(request.getParameter("age")),
                    request.getParameter("phone")));
        } catch (NumberFormatException e) {
            response.setStatus(400);
            return;
        }

        response.sendRedirect(getServletContext().getContextPath() + "/user");

    }
}