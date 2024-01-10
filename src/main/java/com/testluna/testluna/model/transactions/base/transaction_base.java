package com.testluna.testluna.model.transactions.base;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="transaction")
public class transaction_base {
    
    @Id
    @Column(name="transaction_id")
    public String invoiceno;    
    @Column(name="transaction_date", columnDefinition = "timestamp(6) without time zone")
    public Date invoicedate;
    public String note;   
    public Integer status;
}
