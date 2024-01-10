package com.testluna.testluna.repository.baseRepos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testluna.testluna.model.transactions.base.transaction_base;

@Repository
public interface TransactionBaseRepository extends JpaRepository<transaction_base, String> {
    List<transaction_base>findAll();    
}