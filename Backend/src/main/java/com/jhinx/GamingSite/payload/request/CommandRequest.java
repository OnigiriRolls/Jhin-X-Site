package com.jhinx.GamingSite.payload.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class CommandRequest {

    private long userId;
    private long prodId;
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
