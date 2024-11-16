package com.example.rsassigment.controllers;

import com.example.rsassigment.models.Criteria;
import com.example.rsassigment.models.Review;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Flux<Review> reviewStream(@RequestParam(required = false) String restaurantName, @RequestParam(required = false) Integer rating) {
        //url params


//      Flux<Review> reviewFlux = reviewService.streamReviewUpdates()
//                .filter(review -> {
//                    System.out.println("Review Name: " + review.getRestaurantName());
//                    System.out.println("Criteria Name: " + restaurantName);
//                    System.out.println("Review Rating: " + review.getRating());
//                    System.out.println("Criteria Rating: " + rating);
//
//                    //Validation for if the critera matches the review, if it does return true and continue
//                    //onto doOnNext, if not return false and just return the reviewflux to the reviews.html
//                    return reviewService.validateCriteria(restaurantName, rating, review);
//                })
//                .doOnNext(review -> {
//                    reviewService.insertReview(review).subscribe(
//                            success -> System.out.println("Saved to DB: " + review),
//                            error -> System.err.println("Failed to save to DB: " + error.getMessage())
//                    );
//                })
//                .doOnError(error -> {
//                    System.out.println("Error: " + error.getMessage());
//                });

        return reviewService.streamReviewUpdates()
                .filter(review -> {
                    // Each review is passed to the service layer where it is validated against the criteria
                    // to see if it should be inserting into the db, if the criteria is met, the review is added
                    // the return true, returns the review to the front end to be displayed displayed regardless
                    // if the review was saved to the DB
                    reviewService.insertReview(restaurantName, rating, review).subscribe(
                            success -> System.out.println("Saved to DB: " + review),
                            error -> System.err.println("Failed to save to DB: " + error.getMessage())
                    );
                    return true;
                });
    }


}
