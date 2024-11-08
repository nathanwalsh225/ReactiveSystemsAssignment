package com.example.rsassigment.controllers;

import com.example.rsassigment.models.Review;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import com.example.rsassigment.service.ReviewService;

@RestController
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(path ="/reviewStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Review> reviewStream() {
        return reviewService.streamReviewUpdates();
    }
}
