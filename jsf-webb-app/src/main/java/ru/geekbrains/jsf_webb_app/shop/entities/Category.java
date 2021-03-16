package ru.geekbrains.jsf_webb_app.shop.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Category.getAll", query = "from Category"),
        @NamedQuery(name = "Category.getById", query = "from Category c where c.id = :id"),
        @NamedQuery(name = "Category.getByName", query = "from Category c where c.name like :name")
})


@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long Id;
    @Column
    private String name;

    @Serial
    @Transient
    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "category")
    private List<Product> products;

    public Category() {
    }


    public Category(Long id, String name) {
        Id = id;
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
