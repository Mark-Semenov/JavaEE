package ru.geekbrains.jsf_webb_app.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Named
@ApplicationScoped
public class Repository {

    private final Map<Long, Product> productBase = new ConcurrentHashMap<>();
    private final Map<Long, User> userBase = new ConcurrentHashMap<>();
    AtomicLong productId = new AtomicLong(0);
    AtomicLong userId = new AtomicLong(0);

    @PostConstruct
    public void init() {
        addToRepo(new Product(null, "Product1", "Description1", new BigDecimal(5500)));
        addToRepo(new Product(null, "Product2", "Description2", new BigDecimal(11500)));
        addToRepo(new Product(null, "Product3", "Description3", new BigDecimal(30000)));
        addToRepo(new User(null, "Ann", "Taylor", 25, "8999-555-01-01"));
    }

    public void addToRepo(Product product) {
        if (product.getId() == null) {
            Long id = productId.incrementAndGet();
            product.setId(id);
        }
        productBase.put(product.getId(), product);
    }

    public void addToRepo(User user) {
        if (user.getId() == null) {
            Long id = userId.incrementAndGet();
            user.setId(id);
        }
        userBase.put(user.getId(), user);
    }


    public void deleteProduct(Long id) {
        productBase.remove(id);
    }

    public void deleteUser(Long id) {
        userBase.remove(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productBase.values());
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userBase.values());
    }

    public Product getProductById(Long id) {
        return productBase.get(id);
    }

    public User getUserById(Long id) {
        return userBase.get(id);
    }


}
