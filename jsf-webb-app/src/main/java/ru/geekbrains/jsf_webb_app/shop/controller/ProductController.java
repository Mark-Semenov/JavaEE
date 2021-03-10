package ru.geekbrains.jsf_webb_app.shop.controller;

import ru.geekbrains.jsf_webb_app.shop.entities.Category;
import ru.geekbrains.jsf_webb_app.shop.entities.Product;
import ru.geekbrains.jsf_webb_app.shop.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionListener;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;
    private Product selectedProduct;
    private Category category;
    private List<Product> selectedProducts;

    public void createNewProduct(){
        selectedProduct = productService.openNewProduct();
    }

    public List<Product> getProducts() {
        return productService.allProducts();
    }

    public List<Category> getCategories() {
        return productService.allCategories();
    }

    public void saveProduct() {
        productService.saveProduct(selectedProduct);
    }

    public void deleteSelectedProducts(){

    }



    public void deleteProduct() {
        productService.deleteProduct(selectedProduct);
    }







    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }


    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
