package com.example.rsassigment.controllers;

import com.example.rsassigment.models.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String reviews(){
        return "reviews";
    }

    //catch post request here

    @PostMapping("/saveCriteria")
    public String saveCriteria(@ModelAttribute Criteria criteria, Model model) {

        model.addAttribute("criteria", criteria);
        return "reviews";
    }
}
