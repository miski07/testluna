package com.testluna.testluna.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testluna.testluna.model.product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<product, String> {
    List<product>findAll();    
}
