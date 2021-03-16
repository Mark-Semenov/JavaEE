package ru.geekbrains.jsf_webb_app.shop.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductView  {

    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryName;
    private Long categoryId;
    private int quantity;
    private List<ProductView> productList;


    public ProductView() {
    }


    public ProductView build(Product product) {
        this.id = product.getId();
        this.code = product.getCode();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.categoryName = product.getCategory().getName();
        this.categoryId = product.getCategory().getId();
        this.quantity = product.getQuantity();
        return this;
    }


    public List<ProductView> build(List<Product> products) {
        this.productList = new ArrayList<>();
        for (Product product : products) {
            this.productList.add(new ProductView().build(product));
        }
        return this.productList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<ProductView> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductView> productList) {
        this.productList = productList;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
