package com.example.rsassigment.service;

import com.example.rsassigment.models.Review;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class ReviewService {

    private WebClient webClient;

    public ReviewService() {
        this.webClient = WebClient.create("https://my.api.mockaroo.com/review.json?key=aa67b4c0&format=json");
    }

    public Mono<Review> getReviews(String restaurantName, String address, Integer rating, String menuItem, Date openingHours, Date closingHours, Boolean deliveryAvailable, String reviewText)
    {
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

        public Flux<Review> streamReviewUpdates()
        {
                // Publish a new element every 10 seconds
            return Flux.interval(Duration.ofSeconds(10))
                    .flatMap(tick -> getReviews("restaurantName", "address", 5, "menuItem", new Date(), new Date(), true, "reviewText"));

        }
    }