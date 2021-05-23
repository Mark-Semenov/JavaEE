package ru.geekbrains.jsf_webb_app.shop.service;


import ru.geekbrains.jsf_webb_app.shop.DAO.CategoryDAO;
import ru.geekbrains.jsf_webb_app.shop.DAO.ProductDAO;
import ru.geekbrains.jsf_webb_app.shop.entities.Category;
import ru.geekbrains.jsf_webb_app.shop.entities.CategoryView;
import ru.geekbrains.jsf_webb_app.shop.entities.Product;
import ru.geekbrains.jsf_webb_app.shop.entities.ProductView;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductRestService implements ProductServiceAPI {

    @EJB
    private ProductDAO productDAO;

    @EJB
    private CategoryDAO categoryDAO;


    @Override
    public List<ProductView> getProducts() {
        return new ProductView().build(productDAO.getAllProducts());
    }

    @Override
    public ProductView getProductById(Long id) {
        Product product = productDAO.getProductByID(id);
        return new ProductView().build(product);
    }

    @Override
    public CategoryView getCategoryById(Long id) {
        Category category = categoryDAO.categoryById(id);
        return new CategoryView().build(category);
    }

    @Override
    public CategoryView getCategoryByName(String name) {
        Category category = categoryDAO.categoryByName(name);
        return new CategoryView().build(category);
    }

    @Override
    public List<ProductView> getProductByName(String name) {
        return new ProductView().build(productDAO.getProductByName(name));
    }

    @Override
    public List<ProductView> getProductsByCategoryId(Long id) {
        return new ProductView().build(productDAO.getProductsByCategoryId(id));
    }

    @Override
    public void updateProduct(ProductView product) {
        Product product1 = productDAO.getProductByID(product.getId());
        product1.setName(product.getName());
        product1.setCode(product.getCode());
        product1.setDescription(product.getDescription());
        product1.setCategory(categoryDAO.categoryById(product.getCategoryId()));
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        productDAO.updateProduct(product1);
    }

    @Override
    public void addProduct(ProductView productView) {
        productDAO.createProduct(new Product(productView.getCode(), productView.getName(),
                productView.getDescription(), productView.getPrice(),
                categoryDAO.categoryById(productView.getCategoryId()),
                productView.getQuantity()));
    }

    @Override
    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }
}