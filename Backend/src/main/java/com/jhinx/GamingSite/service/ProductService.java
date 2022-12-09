package com.jhinx.GamingSite.service;

import com.jhinx.GamingSite.model.Product;
import com.jhinx.GamingSite.model.User;
import com.jhinx.GamingSite.repository.ProductRepository;
import com.jhinx.GamingSite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Map<Long, String> getPhotosMap() {
        List<Product> products = productRepository.findAll();

        return products.stream().collect(Collectors.toMap(u->u.getId(),u->u.getPhotoUrl()));
    }

    public Product saveProduct(Product product) {
        if(!existsByName(product.getName()))
        return productRepository.save(product);

        return product;
    }

    public boolean existsByName(String name) {
        List<Product> products = productRepository.findAll();

        return products.stream().map((u)->u.getName())
                .filter((u)->u.equals(name)).findFirst().isPresent();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public Product getById(Long id){
        return productRepository.getReferenceById(id);
    }

    public String subQuantity(Long id)
    {
        Product prod = getById(id);
        int aux = Integer.valueOf(prod.getQuantity());
        aux--;
        prod.setQuantity(String.valueOf(aux));

        productRepository.save(prod);

        return "Quantity reduced";
    }
}
