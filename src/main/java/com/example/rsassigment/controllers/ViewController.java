package com.example.rsassigment.controllers;

import com.example.rsassigment.models.Criteria;
import com.example.rsassigment.models.Review;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewController {

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String reviews(Model model){
        model.addAttribute("criteria", new Criteria());
        model.addAttribute("review", new Review());
        return "reviews";
    }

    //catch post request here because you cannot load html pages in a REST controller
    @PostMapping("/saveCriteria")
    public String saveCriteria(@ModelAttribute("criteria") Criteria criteria, Model model) {
        model.addAttribute("criteria", criteria);

        System.out.println(criteria.getRating());
        System.out.println(criteria.getRestaurantName());

        return "reviews";
    }
}
