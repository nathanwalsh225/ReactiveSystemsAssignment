package com.example.rsassigment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/reviews")
    public String reviews(){
        return "reviews";
    }
}
