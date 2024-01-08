package com.testluna.testluna.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class product {    
    @Id
    public String stock_id;
    public String name;
    public String unit;
    public String category;
    public double cost;
    public double price;
    public String taxid;
}
