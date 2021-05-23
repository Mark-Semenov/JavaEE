package ru.geekbrains.first_web_app.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Repository {

    private final Map<Long, Product> productBase = new ConcurrentHashMap<>();
    private final Map<Long, User> userBase = new ConcurrentHashMap<>();
    AtomicLong productId = new AtomicLong(0);
    AtomicLong userId = new AtomicLong(0);

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
