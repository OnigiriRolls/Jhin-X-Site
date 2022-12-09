package com.jhinx.GamingSite.exceptions;

import com.jhinx.GamingSite.model.Product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Could not found the product with id "+ id);
    }
}
