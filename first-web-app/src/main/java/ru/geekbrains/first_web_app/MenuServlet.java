package ru.geekbrains.first_web_app;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "menu", urlPatterns = "/menu-servlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String header = (String) request.getAttribute("header");
        response.getWriter().println("<h1>" + header + "</h1");

        String s = request.getServletContext().getContextPath();
        Set<String> paths = getServletConfig().getServletContext().getResourcePaths("/");
        for (String path : paths) {
            response.getWriter().println("<p><a href=" + s + path + ">Главная</a></p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
