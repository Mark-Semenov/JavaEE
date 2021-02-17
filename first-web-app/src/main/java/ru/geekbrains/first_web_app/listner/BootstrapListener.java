package ru.geekbrains.first_web_app.listner;

import ru.geekbrains.first_web_app.persist.Product;
import ru.geekbrains.first_web_app.persist.Repository;
import ru.geekbrains.first_web_app.persist.User;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Repository productRepository = new Repository();
        Repository userRepository = new Repository();


        productRepository.addToRepo(new Product(null, "Product1", "Description1", new BigDecimal(5500)));
        productRepository.addToRepo(new Product(null, "Product2", "Description2", new BigDecimal(11500)));
        productRepository.addToRepo(new Product(null, "Product3", "Description3", new BigDecimal(30000)));


        userRepository.addToRepo(new User(null, "Ann", "Taylor", 25, "8999-555-01-01"));


        sce.getServletContext().setAttribute("repo", productRepository);
        sce.getServletContext().setAttribute("userRepo", userRepository);

    }
}
