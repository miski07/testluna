package com.testluna.testluna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.testluna.testluna.model.product;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController 
{
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public Iterable<product> getAllProducts() {
        return productService.findAll();
    }
}
