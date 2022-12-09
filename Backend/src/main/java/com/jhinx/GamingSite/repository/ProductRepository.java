package com.jhinx.GamingSite.repository;

import com.jhinx.GamingSite.model.Product;
import com.jhinx.GamingSite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
