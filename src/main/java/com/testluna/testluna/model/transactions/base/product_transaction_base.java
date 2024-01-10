package com.testluna.testluna.model.transactions.base;

import java.util.UUID;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product_transaction")
public class product_transaction_base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID uuid;

    @Column(name="transaction_id")
    public String invoiceNo;

    @Column(name="product_id")
    public String product_id;
    public int qty;
}
