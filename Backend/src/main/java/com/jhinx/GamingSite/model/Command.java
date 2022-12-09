package com.jhinx.GamingSite.model;

import javax.persistence.*;

@Entity
@Table(name = "Command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "id_user")
    private long userId;

    @Column(name = "id_prod")
    private long prodId;

    public Command(){}

    public Command(String address, long userId, long prodId) {
        this.address = address;
        this.userId = userId;
        this.prodId = prodId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }
}
