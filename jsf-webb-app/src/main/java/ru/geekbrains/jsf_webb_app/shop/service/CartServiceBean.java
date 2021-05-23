package ru.geekbrains.jsf_webb_app.shop.service;

import ru.geekbrains.jsf_webb_app.shop.entities.Product;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartServiceBean implements CartService {

    List<Product> productList = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public void addProductToCart(Product product) {
        if (productList.stream().noneMatch(product1 -> product1.equals(product))) {
            productList.add(product);
        }
        product.getCountInCart().incrementAndGet();
    }

    @Override
    public void removeProductFromCart(Product product) {
        if (product.getId() != null) {
            productList.remove(product);
            product.getCountInCart().set(0);
        }
    }
}
