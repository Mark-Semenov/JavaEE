package ru.geekbrains.jsf_webb_app.shop.service;

import ru.geekbrains.jsf_webb_app.shop.entities.Category;
import ru.geekbrains.jsf_webb_app.shop.entities.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    void createProduct(Product product);

    void deleteProduct(Product product);

    void deleteProducts(List<Product> productList);

    void saveProduct(Product product);

    List<Product> allProducts();

    List<Category> allCategories();

    void findProductById(Long id);

    Product openNewProduct();

    void removeProduct(Product product);
}
