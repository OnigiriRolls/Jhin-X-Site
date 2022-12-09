package com.jhinx.GamingSite.model;

public class CommandView {
    private String prodName;
    private String prodPrice;
    private String address;

    public CommandView(String prodName, String prodPrice, String address) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.address = address;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
