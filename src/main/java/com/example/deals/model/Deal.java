package com.example.deals.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deal {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String url;

    public Deal() {
    }

    public Deal(Long id, String title, String description, double price, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.url = url;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
