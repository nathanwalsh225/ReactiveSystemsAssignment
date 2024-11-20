package com.example.rsassigment.controllers;

import com.example.rsassigment.models.Criteria;
import com.example.rsassigment.models.Review;
import com.example.rsassigment.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
public class ViewController {

    private final ReviewService reviewService;

    public ViewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


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

        return "reviews";
    }

    @GetMapping("/savedReviews")
    public String savedReviews(Model model) {
        model.addAttribute("criteria", new Criteria());
        return "savedReviews";
    }

    //returning the restaurant name to the savedReviews page to query the data by name
    @PostMapping("/savedReviews")
    public String savedReviews(@ModelAttribute("criteria") Criteria criteria, Model model) {
        model.addAttribute("criteria", criteria);

        return "savedReviews";
    }

    @GetMapping("/reviews/delete")
    public String deleteReview(@RequestParam String id) {

        reviewService.deleteReview(id).subscribe();
        return "redirect:/savedReviews";
    }

    @GetMapping("/reviews/edit")
    public Mono<String> editReview(@RequestParam String id, Model model) {
        //Passing the review object into the form to populate the fields with the current data
        return reviewService.getReviewById(id)
                .doOnNext(review -> model.addAttribute("review", review))
                .then(Mono.just("editReview"))
                .onErrorResume(e -> {
                    // if it fails log the error
                    System.err.println("Error fetching review by ID: " + e.getMessage());
                    return Mono.empty();
                });
    }

    @PostMapping("/reviews/update")
    public Mono<String> updateReview(@ModelAttribute Review review) {
        //Updating the review object with the new data and redirecting the user back to the savedReviews page
        return reviewService.updateReview(review)
                .then(Mono.just("redirect:/savedReviews"))
                .onErrorResume(error -> {
            System.err.println("Error updating review: " + error.getMessage());
            return Mono.empty();
        });
    }



}
