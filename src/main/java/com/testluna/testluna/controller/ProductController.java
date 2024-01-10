package com.testluna.testluna.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.testluna.testluna.model.product;
import com.testluna.testluna.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/product")
public class ProductController 
{
    private final ProductService productService;
    
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("")
    public product createProduct(@RequestBody product product, @RequestHeader String token) {
        if(token.equals("AAAA"))
        {
            String defaultID = String.valueOf(productService.findLastDefaultIndex());
            product.stock_id= (product.stock_id==null)? "DEF"+ defaultID : product.stock_id;
            productService.save(product);
            return product;
        }
        else{
        product = new product();
        product.stock_id="Error";
        return product;

        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@RequestHeader String stock_id){
        boolean deleted = productService.deleteProduct(stock_id);
        if(deleted){            
            return ResponseEntity.ok("Succesfully Deleted!");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed");
        }

    }

    @GetMapping("/{stock_id}")
    public Iterable<product> geProduct(@RequestParam(required=false) String stock_id){
        if(stock_id==null){
            return productService.findAll();
        }else
        {
            List<product> tmp = new ArrayList<>();
            tmp.add(productService.getProduct(stock_id));
            return tmp;
        }
    }
    
    
    
}
