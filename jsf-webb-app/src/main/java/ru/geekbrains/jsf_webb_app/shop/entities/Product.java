package ru.geekbrains.jsf_webb_app.shop.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;


@NamedQueries({
        @NamedQuery(name = "Product.getAll", query = "from Product"),
        @NamedQuery(name = "Product.deleteById", query = "DELETE FROM Product p WHERE p.id = :id"),
        @NamedQuery(name = "Product.delete", query = "DELETE FROM Product p WHERE p = :p"),
        @NamedQuery(name = "Product.getById", query = "from Product p where p.id = :id"),
        @NamedQuery(name = "Product.count", query = "select count (*) from Product"),
        @NamedQuery(name = "Product.getCategory", query = "from Product p inner join Category c where c.id = :category_id")


})

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "products")
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private BigDecimal price;

    @ManyToOne
    private Category category;


    @Column
    private int quantity;

    @Column
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    @Column
    private int rating;


    @Transient
    private AtomicInteger countInCart = new AtomicInteger(0);

    @Transient
    private static final long serialVersionUID = 1L;

    public Product() {
    }

    public Product(Long id, String code, String name, String description, String image, BigDecimal price, Category category, int quantity, InventoryStatus inventoryStatus, int rating) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.inventoryStatus = inventoryStatus;
        this.rating = rating;
    }

    public Product(Category category) {
        this.category = category;
    }

    public Product clone() {
        return new Product(getId(), getCode(), getName(), getDescription(), getImage(), getPrice(), getCategory(), getQuantity(), getInventoryStatus(), getRating());
    }


    public AtomicInteger getCountInCart() {
        return countInCart;
    }

    public void setCountInCart(AtomicInteger countInCart) {
        this.countInCart = countInCart;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public int getRating() {
        return rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (code == null) {
            return other.code == null;
        } else return code.equals(other.code);
    }

}
