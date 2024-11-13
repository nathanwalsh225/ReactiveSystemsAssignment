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

    public Boolean insertReview(String restaurantName, String address, Integer rating, String menuItem, Integer openingHours, Integer closingHours, Boolean deliveryAvailable, String reviewText) {

            Review review = new Review();

            review.setRestaurantName(restaurantName);
            review.setAddress(address);
            review.setRating(rating);
            review.setMenuItem(menuItem);
            review.setOpeningHours(openingHours);
            review.setClosingHours(closingHours);
            review.setDeliveryAvailable(deliveryAvailable);
            review.setReviewText(reviewText);

            System.out.println("Name: " + review.getRestaurantName());
            System.out.println("Address: " + review.getAddress());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Menu item: " + review.getMenuItem());
            System.out.println("Open: " + review.getOpeningHours());
            System.out.println("Close: " + review.getClosingHours());
            System.out.println("Delivery: " + review.getDeliveryAvailable());
            System.out.println("Text: " + review.getReviewText());

            System.out.println("Review inserted: " + review.getRestaurantName());

//            try (reviewRepository.insert(review)) {
//                return true;
//            } catch (Exception e) {
//                System.out.println("Error: " + e.getMessage());
//                return false;
//            }

        return true;

    }


}