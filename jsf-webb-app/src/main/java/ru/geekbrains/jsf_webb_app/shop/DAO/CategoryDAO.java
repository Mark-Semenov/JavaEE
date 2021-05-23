package ru.geekbrains.jsf_webb_app.shop.DAO;

import ru.geekbrains.jsf_webb_app.shop.entities.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CategoryDAO implements Serializable {

    @PersistenceContext(unitName = "PostgresPU")
    private EntityManager entityManager;

    private List<Category> categories = new ArrayList<>();
    private Category category;


    public List<Category> getCategories() {
        categories = entityManager.createNamedQuery("Category.getAll", Category.class).getResultList();
        return categories;
    }

    public void deleteCategory(Category category){
        entityManager.remove(category);
    }

    public void changeCategory(Category category){
        entityManager.merge(category);
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

}
