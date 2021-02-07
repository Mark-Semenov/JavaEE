package ru.geekbrains.first_web_app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet (name = "myFirstServlet", urlPatterns = "/first-servlet")
public class FirstServlet implements Servlet {
    ServletConfig servletConfig;
    Logger logger = LoggerFactory.getLogger(FirstServlet.class.getName());

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        logger.info("Successful initialization");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        logger.info("Request received");
        response.getWriter().println("<h1>Hello World!<h1>");
        logger.info("Response sent");

    }

    @Override
    public String getServletInfo() {
        return servletConfig.getServletName();
    }

    @Override
    public void destroy() {

    }
}