package com.example.rsassigment.controllers;

import com.example.rsassigment.models.Criteria;
import com.example.rsassigment.models.Review;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import com.example.rsassigment.service.ReviewService;
import reactor.core.publisher.Mono;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(path ="/reviewStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Review> reviewStream() {
        //url params
        return reviewService.streamReviewUpdates();
    }

    @GetMapping("/test")
    public String testSave() {
        return null;
    }

    @GetMapping("/saveCriteria")
    public String saveCriteria(Model model) {
        model.addAttribute("criteria", new Criteria());

        return "reviews";
    }




}
