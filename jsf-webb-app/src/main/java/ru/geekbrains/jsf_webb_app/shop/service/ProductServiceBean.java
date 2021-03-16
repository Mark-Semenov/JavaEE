package ru.geekbrains.jsf_webb_app.shop.service;


import ru.geekbrains.jsf_webb_app.shop.DAO.CategoryDAO;
import ru.geekbrains.jsf_webb_app.shop.DAO.ProductDAO;
import ru.geekbrains.jsf_webb_app.shop.entities.Category;
import ru.geekbrains.jsf_webb_app.shop.entities.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;

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
        if (product != null) {
            productDao.createProduct(product);
        }
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

    @Override
    @TransactionAttribute
    public void saveProduct(Product selectedProduct) {
        productDao.updateProduct(selectedProduct);
    }

    @Override
    @TransactionAttribute
    public void removeProduct(Product selectedProduct) {
        productDao.deleteProduct(selectedProduct);
    }

    @Override
    @TransactionAttribute
    public void deleteProducts(List<Product> selectedProducts) {
        if (!selectedProducts.isEmpty()) {
            productDao.deleteProducts(selectedProducts);
        } else throw new NullPointerException("Products collection is empty");
    }

    @Override
    public List<Category> allCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        return products;
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
}