package ru.geekbrains.jsf_webb_app.shop.controller;

import org.primefaces.PrimeFaces;
import ru.geekbrains.jsf_webb_app.shop.entities.Category;
import ru.geekbrains.jsf_webb_app.shop.entities.Product;
import ru.geekbrains.jsf_webb_app.shop.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;
    private Product selectedProduct;
    private List<Product> selectedProducts = new ArrayList<>();

    public void createNewProduct() {
        selectedProduct = productService.openNewProduct();
    }

    public List<Product> getProducts() {
        return productService.allProducts();
    }

    public List<Category> getCategories() {
        return productService.allCategories();
    }

    public void saveProduct() {
        if (selectedProduct.getCode() == null) {
            selectedProduct.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            productService.createProduct(selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        } else {
            productService.saveProduct(selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-product");

    }

    public void deleteProduct() {
        productService.removeProduct(selectedProduct);
        selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-product");
    }

    public void deleteSelectedProducts() {
        productService.deleteProducts(selectedProducts);
        selectedProducts = null;
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
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
