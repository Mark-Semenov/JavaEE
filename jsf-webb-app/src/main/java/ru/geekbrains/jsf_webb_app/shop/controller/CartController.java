package ru.geekbrains.jsf_webb_app.shop.controller;

import ru.geekbrains.jsf_webb_app.shop.entities.Product;
import ru.geekbrains.jsf_webb_app.shop.service.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService service;

    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    public void addProductToCart(Product product) {
        service.addProductToCart(product);
    }

    public void deleteProduct(Product product) {
        service.removeProductFromCart(product);
    }

}
