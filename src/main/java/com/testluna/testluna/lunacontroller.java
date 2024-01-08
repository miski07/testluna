package com.testluna.testluna;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class lunacontroller {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

}
