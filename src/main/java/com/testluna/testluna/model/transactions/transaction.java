package com.testluna.testluna.model.transactions;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class transaction{

    @Id
    @Column(name="transaction_id")
    public String invoiceno;    
    @Column(name="transaction_date", columnDefinition = "timestamp(6) without time zone")
    public Date invoicedate;
    public String note;  
    public Integer status;
    public String email;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    public List<product_transaction> itemLines;
}
