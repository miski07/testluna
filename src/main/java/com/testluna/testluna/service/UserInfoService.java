package com.testluna.testluna.service;

import org.springframework.stereotype.Service;

import com.testluna.testluna.model.logins.user_info;
import com.testluna.testluna.repository.UserInfoRepository;

@Service
public class UserInfoService {
    private final UserInfoRepository userRepository;

    public UserInfoService(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<user_info> findAll() {
        Iterable<user_info> userInfo = userRepository.findAll();
        return userInfo;
    }
    public user_info findUser_info(String email){
        user_info user_info = userRepository.findByEmail(email);
        return user_info;
    }

    public void save(user_info user_info){
        userRepository.save(user_info);
    }
}
