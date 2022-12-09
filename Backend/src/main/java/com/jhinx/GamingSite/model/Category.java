package com.jhinx.GamingSite.model;

import javax.persistence.*;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_prod")
    private long prodId;

    public Category(String name, long prodId) {
        this.name = name;
        this.prodId = prodId;
    }

    public Category() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdProd(long prodId) {
        this.prodId = prodId;
    }

    public long getIdProd() {
        return prodId;
    }
}
