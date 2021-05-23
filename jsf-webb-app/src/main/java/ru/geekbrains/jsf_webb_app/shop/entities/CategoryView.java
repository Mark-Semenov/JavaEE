package ru.geekbrains.jsf_webb_app.shop.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CategoryView implements Serializable {

    private Long id;
    private String name;


    public CategoryView() {
    }

    public CategoryView build(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
