package ru.geekbrains.jsf_webb_app.cart;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    private Product selectedProduct;
    private final List<Product> products = new ArrayList<>();

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

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }


    public void deleteProduct(Product product) {
        products.remove(product);
        product.setQuantity(product.getQuantity() + product.getCountInCart().get());
    }


    public long totalSum() {
        long sum = 0;
        for (Product i : products) {
            sum += i.totalSum();
        }
        return sum;
    }
}
