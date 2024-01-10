package com.testluna.testluna.model.logins;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_info")
public class user_info {    
    @Id
    public String email;
    public String fullName;
    public String password;
    public String companyName;
}

