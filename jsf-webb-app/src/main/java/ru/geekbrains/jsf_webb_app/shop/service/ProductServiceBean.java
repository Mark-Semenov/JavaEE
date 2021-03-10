package ru.geekbrains.jsf_webb_app.shop.service;


import org.primefaces.PrimeFaces;
import ru.geekbrains.jsf_webb_app.shop.DAO.CategoryDAO;
import ru.geekbrains.jsf_webb_app.shop.DAO.ProductDAO;
import ru.geekbrains.jsf_webb_app.shop.entities.Category;
import ru.geekbrains.jsf_webb_app.shop.entities.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.UUID;

@Stateless
public class ProductServiceBean implements ProductService {

    @EJB
    private ProductDAO productDao;
    @EJB
    private CategoryDAO categoryDAO;

    private List<Product> products;
    private List<Category> categories;


    @PostConstruct
    public void init() {
        this.products = productDao.getAllProducts();
        this.categories = categoryDAO.getCategories();
    }

    @Override
    public void createProduct(Product product) {
        productDao.createProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        if (product.getId() != null && !products.contains(product))
            productDao.deleteProduct(product);
    }


    public List<Product> allProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void findProductById(Long id) {
        if (id != null) {
            productDao.getProductByID(id);
        } else throw new IllegalArgumentException("Incorrect id value");
    }

    @Override
    public Product openNewProduct() {
        return new Product(new Category());
    }


//    public List<Product> getProductsView() {
//        return products = getAllProducts();
//    }


    @Override
    @TransactionAttribute
    public void saveProduct(Product selectedProduct) {
        if (selectedProduct.getCode() == null) {
            selectedProduct.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            createProduct(selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        } else {
            productDao.updateProduct(selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

//        getProductsView();
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-product");
    }

    @Override
    @TransactionAttribute
    public void removeProduct(Product selectedProduct) {
//        entityManager.createNamedQuery("Product.delete").setParameter("p", this.selectedProduct).executeUpdate();
        deleteProduct(selectedProduct);
//        selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
//        PrimeFaces.current().ajax().update("form:messages", "form:dt-product");
//        getProductsView();
    }


    @Override
    @TransactionAttribute
    public void deleteProducts(List<Product> selectedProducts) {
        if (!selectedProducts.isEmpty()) {
            productDao.deleteProducts(selectedProducts);
        } else throw new NullPointerException("Products collection is empty");
        productDao.deleteProducts(selectedProducts);

//        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
//        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
//        getProductsView();
    }

    @Override
    public List<Category> allCategories() {
        return categories;
    }


}