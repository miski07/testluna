package com.testluna.testluna.service;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;
import com.testluna.testluna.model.product;
import com.testluna.testluna.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public product getProduct(String stock_id){
        Optional<product> product = productRepository.findById(stock_id);
        return product.get();
    }
    public Iterable<product> findAll() {
        Iterable<product> products = productRepository.findAll();
        return products;
    }
    public int findLastDefaultIndex(){
        Iterable<product> products = productRepository.findAll();
        String stockID= "DEF0000";
        for(product p : products){
            if(p.stock_id!=null){
                String [] tmp = p.stock_id.split("DEF");
                stockID= (tmp.length>1)? tmp[1]: stockID; 
            }
        }
        return Integer.parseInt(stockID)+1;
    }

    public boolean deleteProduct(String stock_id){
        product product = getProduct(stock_id);
        if(product==null){
            return false;
        }
        productRepository.delete(product);
        return true;
    }
    public void save(product product){
        productRepository.save(product);
    }
}
