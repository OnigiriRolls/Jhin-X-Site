package com.jhinx.GamingSite.controllers;

import com.jhinx.GamingSite.exceptions.ProductNotFoundException;
import com.jhinx.GamingSite.exceptions.UserNotFoundException;
import com.jhinx.GamingSite.model.Command;
import com.jhinx.GamingSite.model.CommandView;
import com.jhinx.GamingSite.model.Product;
import com.jhinx.GamingSite.model.User;
import com.jhinx.GamingSite.payload.request.ProductRequest;
import com.jhinx.GamingSite.payload.response.MessageResponse;
import com.jhinx.GamingSite.repository.ProductRepository;
import com.jhinx.GamingSite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prod")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/photos")
    public Map<Long,String> getPhotosMap () {
        return productService.getPhotosMap();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody ProductRequest prodReq){
        Product prod = new Product(prodReq.getName(),prodReq.getPrice(),prodReq.getQuantity(),
                prodReq.getDescription(),prodReq.getPhotoUrl());

        System.out.println(prod);

        if(productService.existsByName(prod.getName()))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Product is already added!"));

        productService.saveProduct(prod);

        return ResponseEntity.ok(new MessageResponse("Product added successfully!"));
    }

    @GetMapping("/getAll")
    public List<Product> list() {
        return productService.getAllProducts();
    }

    @GetMapping ("/getByName")
    public List<Product> listByName(@RequestParam String name) {
        List<Product> aux = list();

        String auxName = name.toUpperCase();

        return aux.stream().filter((prod)->prod.getName().toUpperCase().contains(auxName)).collect(Collectors.toList());
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Product prodReq) {
        if (productService.existsById(prodReq.getId()))
        {
            Product old = productService.getById(prodReq.getId());

        if(prodReq.getName()!=null && !prodReq.getName().isEmpty())
        old.setName(prodReq.getName());
            if(prodReq.getDescription()!=null && !prodReq.getDescription().isEmpty())
        old.setDescription(prodReq.getDescription());
            if(prodReq.getPrice()!=null && !prodReq.getPrice().isEmpty())
        old.setPrice(prodReq.getPrice());
            if(prodReq.getPhotoUrl()!=null && !prodReq.getPhotoUrl().isEmpty())
        old.setPhotoUrl(prodReq.getPhotoUrl());
            if(prodReq.getQuantity()!=null && !prodReq.getQuantity().isEmpty())
        old.setQuantity(prodReq.getQuantity());

        productRepository.save(old);
        return ResponseEntity.ok(new MessageResponse("Product updated successfully!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Product doesn`t exist"));
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        if(!productService.existsById(id)){
            throw new ProductNotFoundException(id);
        }
        productService.deleteById(id);
        return  "Product with id "+id+" has been deleted successfully.";
    }
}
