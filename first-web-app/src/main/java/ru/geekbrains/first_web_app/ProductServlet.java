package ru.geekbrains.first_web_app;

import ru.geekbrains.first_web_app.persist.Product;
import ru.geekbrains.first_web_app.persist.Repository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "product", urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private Repository productRepo;


    @Override
    public void init() throws ServletException {
        this.productRepo = (Repository) getServletContext().getAttribute("repo");
        if (productRepo == null) {
            throw new ServletException("Репозиторий не инициализирован");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo() == null || request.getPathInfo().equals("/product")) {
            request.setAttribute("products", productRepo.getAllProducts());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);
        } else if (request.getPathInfo().equals("/edit")) {
            Long id;
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException ex) {
                response.setStatus(400);
                return;
            }
            Product product = productRepo.getProductById(id);
            if (product == null) {
                response.setStatus(404);
                return;
            }
            request.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(request, response);
        } else if (request.getPathInfo().equals("/delete")) {
            Long id = Long.parseLong(request.getParameter("id"));
            productRepo.deleteProduct(id);
            response.sendRedirect(getServletContext().getContextPath() + "/product");
        } else if (request.getPathInfo().equals("/add")) {
            getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id;

        try {
            if (request.getParameter("id").equals("")) {
                id = null;
            } else id = Long.parseLong(request.getParameter("id"));

            productRepo.addToRepo(new Product(id,
                    request.getParameter("name"),
                    request.getParameter("description"),
                    new BigDecimal(request.getParameter("price"))));
        } catch (NumberFormatException e) {
            response.setStatus(400);
            return;
        }

        response.sendRedirect(getServletContext().getContextPath() + "/product");

    }
}