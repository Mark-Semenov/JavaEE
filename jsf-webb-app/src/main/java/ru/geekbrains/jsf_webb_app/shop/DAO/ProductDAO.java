package ru.geekbrains.jsf_webb_app.shop.DAO;


import ru.geekbrains.jsf_webb_app.shop.entities.Product;
import ru.geekbrains.jsf_webb_app.shop.entities.ProductView;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductDAO {

    List<Product> products;

    @PersistenceContext(unitName = "PostgresPU")
    private EntityManager entityManager;

    public void createProduct(Product product) {
        entityManager.persist(product);
    }

    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    public void deleteProduct(Product product) {
        entityManager.createNamedQuery("Product.delete").setParameter("p", product).executeUpdate();
    }

    public List<Product> getProductByName(String name) {
       return entityManager.createNamedQuery("Product.getByName", Product.class).setParameter("name", name).getResultList();
    }

    public void deleteProduct(Long id) {
        entityManager.remove(getProductByID(id));
    }

    public void deleteProducts(List<Product> productList) {
        for (Product product : productList) {
            entityManager.createNamedQuery("Product.delete").setParameter("p", product).executeUpdate();
        }
    }

    public Product getProductByID (Long id){
       return entityManager.find(Product.class, id);
    }

    public List<Product> getAllProducts(){
        return products = entityManager.createNamedQuery("Product.getAll", Product.class).getResultList();
    }


    public List<Product> getProductsByCategoryId(Long id){
     return entityManager.createNamedQuery("Product.getCategory", Product.class).setParameter("category_id", id).getResultList();
    }

}
