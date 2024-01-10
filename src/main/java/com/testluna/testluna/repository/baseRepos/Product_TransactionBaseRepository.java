package com.testluna.testluna.repository.baseRepos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testluna.testluna.model.transactions.base.product_transaction_base;

import java.util.List;
import java.util.UUID;

@Repository
public interface Product_TransactionBaseRepository extends JpaRepository<product_transaction_base, UUID> {
    List<product_transaction_base>findAll();    
}