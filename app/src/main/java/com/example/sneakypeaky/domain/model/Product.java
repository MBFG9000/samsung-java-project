package com.sneakypeaky.domain.model;

public class Product {
    private final int id;
    private final String title;
    private final double price;
    private final String description;
    private final String category;
    private final String imageUrl;
    private final double ratingRate;
    private final int ratingCount;

    public Product(int id, String title, double price, String description, String category, String imageUrl, double ratingRate, int ratingCount) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.ratingRate = ratingRate;
        this.ratingCount = ratingCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getRatingRate() {
        return ratingRate;
    }

    public int getRatingCount() {
        return ratingCount;
    }
}
