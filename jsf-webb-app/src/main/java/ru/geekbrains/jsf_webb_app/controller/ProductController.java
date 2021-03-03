package ru.geekbrains.jsf_webb_app.controller;

import ru.geekbrains.jsf_webb_app.persist.Product;
import ru.geekbrains.jsf_webb_app.persist.Repository;
import ru.geekbrains.jsf_webb_app.persist.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private Repository repository;

    private Product product;
    private User user;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct (){
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public String editProduct (Product product){
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void removeProduct(Product product){
        repository.deleteProduct(product.getId());
    }

    public List<Product> getAllProducts (){
       return repository.getAllProducts();

    }

    public String saveProduct() {
        repository.addToRepo(product);
        return "/product.xhtml?faces-redirect-true";
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
