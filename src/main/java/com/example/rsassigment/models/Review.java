package com.example.rsassigment.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Review {

    @Id
    @JsonProperty("id")
    String id;

    @JsonProperty("restaurant_name")
    String restaurantName;

    @JsonProperty("address")
    String address;

    @JsonProperty("rating")
    Integer rating;

    @JsonProperty("menu_item")
    String menuItem;

    @JsonProperty("opening_hours")
    Integer openingHours;

    @JsonProperty("closing_hours")
    Integer closingHours;

    @JsonProperty("delivery_available")
    Boolean deliveryAvailable;

    @JsonProperty("review")
    String reviewText;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Integer openingHours) {
        this.openingHours = openingHours;
    }

    public Integer getClosingHours() {
        return closingHours;
    }

    public void setClosingHours(Integer closingHours) {
        this.closingHours = closingHours;
    }

    public Boolean getDeliveryAvailable() {
        return deliveryAvailable;
    }

    public void setDeliveryAvailable(Boolean deliveryAvailable) {
        this.deliveryAvailable = deliveryAvailable;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
