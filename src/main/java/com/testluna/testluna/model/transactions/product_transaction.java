package com.testluna.testluna.model.transactions;

import java.util.UUID;


import com.testluna.testluna.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product_transaction")
public class product_transaction {
    @Id
    public UUID uuid;
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public product product;
    public int qty;
    
}
