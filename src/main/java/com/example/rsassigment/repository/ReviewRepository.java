package com.example.rsassigment.repository;

import com.example.rsassigment.models.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {

}
