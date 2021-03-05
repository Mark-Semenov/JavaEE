package ru.geekbrains.jsf_webb_app.cart;


import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@SessionScoped
public class CrudView implements Serializable {

    private List<Product> products;

    private Product selectedProduct;

    private Category category;

    private List<Product> selectedProducts;

    private List<Category> categories;

    private final Logger logger = LoggerFactory.getLogger(CrudView.class);


    @PersistenceContext(unitName = "PostgresPU")
    private EntityManager entityManager;


    @PostConstruct
    public void init() {
        getProductsView();
        this.categories = entityManager.createNamedQuery("Category.getAll", Category.class).getResultList();
    }

    public List<Product> getProductsView(){
       return this.products = entityManager.createNamedQuery("Product.getAll", Product.class).getResultList();
    }

    public void openNew() {
        this.selectedProduct = new Product();
        selectedProduct.setCategory(new Category());
    }

    public void createProduct() {
        entityManager.persist(this.selectedProduct);
        getProductsView();
    }

    @Transactional
    public void saveProduct() {
        if (this.selectedProduct.getCode() == null) {
            this.selectedProduct.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            createProduct();
            getProductsView();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        } else {
            entityManager.merge(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "dt-product");
    }


    @Transactional
    public void deleteProduct() {
        entityManager.createNamedQuery("Product.delete").setParameter("p", this.selectedProduct).executeUpdate();
        getProductsView();
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-product");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    @Transactional
    public void deleteSelectedProducts() {
        for (Product product : selectedProducts) {
            entityManager.createNamedQuery("Product.delete").setParameter("p", product).executeUpdate();
        }
        getProductsView();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Logger getLogger() {
        return logger;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

}