package com.example.rsassigment.controllers;

import com.example.rsassigment.models.Criteria;
import com.example.rsassigment.models.Review;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public Flux<Review> reviewStream(@ModelAttribute("criteria") Criteria criteria, Model model) {
        //url params
//        model.addAttribute("criteria", criteria);
//        System.out.println("Name: " + criteria.getRestaurantName());
//        System.out.println("Rating: " + criteria.getRating());

//
        Flux<Review> reviewFlux = reviewService.streamReviewUpdates();

        reviewFlux.subscribe(review -> {
            System.out.println("Review: " + review.getRestaurantName());
            System.out.println("Rating: " + review.getRating());

           Boolean reviewAdded = reviewService.insertReview(review.getRestaurantName(), review.getAddress(), review.getRating(), review.getMenuItem(), review.getOpeningHours(), review.getClosingHours(), review.getDeliveryAvailable(), review.getReviewText());

            if(reviewAdded){
                System.out.println("Review added successfully");
            }else{
                System.out.println("Review not added");
            }
        });

        return reviewFlux;



//        return reviewService.streamReviewUpdates().doOnNext((review -> {
//            System.out.println("Review: " + review.getRestaurantName());
//
//
//
//
//
//        }));
    }


}
