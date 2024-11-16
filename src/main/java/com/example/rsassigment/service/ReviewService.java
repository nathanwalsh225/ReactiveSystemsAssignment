package com.example.rsassigment.service;

import com.example.rsassigment.models.Criteria;
import com.example.rsassigment.models.Review;
import com.example.rsassigment.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.constant.Constable;
import java.time.Duration;
import java.util.Date;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private WebClient webClient;

    public ReviewService(ReviewRepository reviewRepository) {
        this.webClient = WebClient.create("https://my.api.mockaroo.com/review.json?key=aa67b4c0");
        this.reviewRepository = reviewRepository;
    }

    public Mono<Review> getReviews() {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.build())
                .retrieve()
                .bodyToMono(Review.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    System.out.println("Error: " + ex.getMessage());
                    return Mono.empty(); // Return an empty Mono on error to prevent issues
                });
    }

    public Flux<Review> streamReviewUpdates() {
        // Publish a new element every 10 seconds
        return Flux.interval(Duration.ofSeconds(10))
                .flatMap(tick -> getReviews());
    }

    public Mono<Void> insertReview(String restaurantName, Integer rating, Review review) {

        //Check if the criteria matches the review, if it does, save to the DB
        if(validateCriteria(restaurantName, rating, review)) {
            return reviewRepository.insert(review).then();
        } else {
            return Mono.empty();
        }
    }

    public Boolean validateCriteria(String restaurantName, Integer rating, Review review) {

        //basic validation for checking that the resturaunt name and rating match the criteria then return true
        if (review.getRestaurantName().equals(restaurantName) && review.getRating() >= rating) {
            System.out.println("ACCEPTED");
            return true;
        } else if (restaurantName.equals("null") && rating <= review.getRating()) {
            //if the resturaunt name is not selected, its value will be "null" which means the user wants to query
            //only by rating, so check to see if the rating matches the criteria
            System.out.println("ACCEPTED");
            return true;
        } else if (rating == 10 && restaurantName.equals(review.getRestaurantName())) {
            //if the resturaunt name is not selected, its value will be "null" which means the user wants to query
            //only by rating, so check to see if the rating matches the criteria
            System.out.println("ACCEPTED");
            return true;
        }

        //None of the criteria match so return false to indicate not to save to the DB
        System.out.println("REJECTED");
        return false;
    }





}