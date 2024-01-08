package com.testluna.testluna.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class transaction{

    @Id
    private String invoiceno;    
    private String invoicedata;
    private String note;
    private String invoiceitemsid;
}
