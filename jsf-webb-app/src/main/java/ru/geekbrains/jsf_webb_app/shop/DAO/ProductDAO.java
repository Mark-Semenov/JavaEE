package ru.geekbrains.jsf_webb_app.shop.DAO;


import ru.geekbrains.jsf_webb_app.shop.entities.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductDAO {

    List<Product> products = new ArrayList<>();

    @PersistenceContext(unitName = "PostgresPU")
    private EntityManager entityManager;

    public void createProduct(Product product) {
        entityManager.persist(product);
    }

    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    public void deleteProduct(Product product) {
        entityManager.remove(product);
    }

    public void deleteProducts(List<Product> productList) {
        for (Product product : productList) {
            entityManager.remove(product);
        }
    }

    public void getProductByID (Long id){
        entityManager.find(Product.class, id);
    }

    public List<Product> getAllProducts(){
        return products = entityManager.createNamedQuery("Product.getAll", Product.class).getResultList();
    }

}
