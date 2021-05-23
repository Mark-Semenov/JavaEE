package ru.geekbrains.jsf_webb_app.cart;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CategoryDAO implements Serializable {

    @PersistenceContext(unitName = "PostgresPU")
    private EntityManager entityManager;

    private List<Category> categories;
    private List<String> namesCategories = new ArrayList<>();
    private Category category;


    public List<Category> getCategories() {
        categories = new ArrayList<>(entityManager.createNamedQuery("Category.getAll", Category.class).getResultList());
        return categories;
    }

    @PostConstruct
    public void init(){
        for (Category category : getCategories()) {
            namesCategories.add(category.getName());
        }
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

    public void setNamesCategories(List<String> namesCategories) {
        this.namesCategories = namesCategories;
    }

    public List<String> getNamesCategories() {
        return namesCategories;
    }
}
