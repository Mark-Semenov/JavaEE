package ru.geekbrains.jsf_webb_app.shop.service;

import ru.geekbrains.jsf_webb_app.shop.entities.Category;
import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    void deleteCategory(Category category);

    void changeCategory(Category category);
}
