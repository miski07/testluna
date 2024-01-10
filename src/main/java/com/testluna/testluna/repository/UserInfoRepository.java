package com.testluna.testluna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testluna.testluna.model.logins.user_info;

@Repository
public interface UserInfoRepository extends JpaRepository<user_info, String> {
    
    List<user_info>findAll();
    user_info findByEmail(String email);
}
