package com.testluna.testluna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testluna.testluna.model.logins.loginData;
import com.testluna.testluna.model.logins.loginResponse;
import com.testluna.testluna.model.logins.user_info;
import com.testluna.testluna.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/user")
public class UserInfoController {
    
    private final UserInfoService userInfoService;
    
    @Autowired
    public UserInfoController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody user_info user_info) {        
        try{
            userInfoService.save(user_info);
        }
        catch(Exception ex){
            return "Error";
        }
        return "Success";
    }

    @PostMapping("/login")
    public loginResponse loginUser(@RequestBody loginData data) {
        loginResponse loginResponse = new loginResponse();
        user_info user = userInfoService.findUser_info(data.email);
        if(user!=null){
            loginResponse.accessToken = generateToken(data.email); //logic for generating toeken needs to be explored more
            loginResponse.user = user;
        }
        return loginResponse;
    }
    public String generateToken(String email){
        return email;
    }

    @GetMapping("/{email}")
    public user_info getUserInfo(String email) {
        return userInfoService.findUser_info(email);
    }
    

}
