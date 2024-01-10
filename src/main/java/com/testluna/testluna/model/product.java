package com.testluna.testluna.model;

import com.testluna.testluna.model.transactions.tax;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "product")
public class product {    
    @Id
    @Column(nullable = true)
    public String stock_id;
    public String name;
    public String unit;
    public String category;
    public double cost;
    public double price;
    
    @ManyToOne
    @JoinColumn(name="taxid", referencedColumnName = "name")
    public tax tax;
}
