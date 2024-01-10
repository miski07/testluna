package com.testluna.testluna.model.transactions;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tax")
public class tax {
    @Id
    private UUID id;
    @Column(name = "name")
    public String name;
    public double rate;
}
