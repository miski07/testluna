package com.testluna.testluna.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.testluna.testluna.model.skeletons.items;
import com.testluna.testluna.model.skeletons.report;
import com.testluna.testluna.model.transactions.product_transaction;
import com.testluna.testluna.model.transactions.transaction;
import com.testluna.testluna.model.transactions.base.product_transaction_base;
import com.testluna.testluna.model.transactions.base.transaction_base;
import com.testluna.testluna.repository.TransactionRepository;
import com.testluna.testluna.repository.baseRepos.Product_TransactionBaseRepository;
import com.testluna.testluna.repository.baseRepos.TransactionBaseRepository;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final TransactionBaseRepository transactionBaseRepository;
    private final Product_TransactionBaseRepository productTransactionBaseRepository;

    public TransactionService (TransactionRepository transactionRepository, 
    TransactionBaseRepository transactionBaseRepository, 
    Product_TransactionBaseRepository productTransactionBaseRepository)
    {
        this.transactionBaseRepository = transactionBaseRepository;
        this.productTransactionBaseRepository = productTransactionBaseRepository;
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(transaction transaction){
        transactionRepository.save(transaction);
    }
    public List<transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public transaction getTransaction(String id){
        return transactionRepository.findById(id).get();
        
    }

    public int getLatestDefaultTransactionID(){
        List<transaction_base> transactions = transactionBaseRepository.findAll();
        int idx = -1;
        for(transaction_base transaction: transactions){
            String [] currentID = transaction.invoiceno.split("T-");
            if(currentID.length>1){
                int currentIdx= Integer.parseInt(currentID[1]);
                idx=(currentIdx>idx)? currentIdx :idx;
            }
        }
        return idx+1;

    }
    public transaction createTransaction(com.testluna.testluna.model.skeletons.cart cart, int type){
        transaction_base transaction = new transaction_base();
        transaction.invoiceno= cart.invoiceNo;
        transaction.invoicedate = cart.invoiceDate;
        transaction.note = cart.note;
        transaction.status = type;
        transactionBaseRepository.save(transaction);
        for(items i : cart.itemLines ){            
            product_transaction_base cartItems = new product_transaction_base();
            cartItems.qty=i.qty;
            cartItems.product_id = i.id;
            cartItems.invoiceNo = cart.invoiceNo;
            productTransactionBaseRepository.save(cartItems);
        }

        return getTransaction(transaction.invoiceno);

    }

    public List<transaction> getTransactionByUser(String email){
        List<transaction> transactions = getAllTransactions();
        List<transaction> thisUserTransactions = new ArrayList<>();
        for(transaction t: transactions){
            if(t.email.equals(email)){
                thisUserTransactions.add(t);
            }
        }
        return thisUserTransactions;
    }
}
