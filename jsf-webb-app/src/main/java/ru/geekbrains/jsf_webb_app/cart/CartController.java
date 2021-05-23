package ru.geekbrains.jsf_webb_app.cart;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    private final List<Product> products = new ArrayList<>();

    private Product product;

    public void addProductToCart(Product product) {
        if (products.stream().noneMatch(product1 -> product1.equals(product))) {
            products.add(product);
        }

        product.getCountInCart().incrementAndGet();
        product.setQuantity(product.getQuantity() - 1);
    }


    public List<Product> getAllProducts() {
        return products;
    }


    public void deleteProduct(Product product) {
        products.remove(product);
        product.setQuantity(product.getQuantity() + product.getCountInCart().get());
    }

}
