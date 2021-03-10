package ru.geekbrains.jsf_webb_app.shop.service;

import ru.geekbrains.jsf_webb_app.shop.entities.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {

    void addProductToCart(Product product);

    void removeProductFromCart(Product product);

    List<Product> getAllProducts();

}
