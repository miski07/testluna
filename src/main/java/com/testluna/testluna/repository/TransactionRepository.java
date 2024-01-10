package com.testluna.testluna.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testluna.testluna.model.transactions.transaction;

@Repository
public interface TransactionRepository extends JpaRepository<transaction, String> {
    List<transaction>findAll();    
}