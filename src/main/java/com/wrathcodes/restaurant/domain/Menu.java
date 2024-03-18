package com.wrathcodes.restaurant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Menu extends GenericDomain {

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false)
    private String description;

    @Column(length = 50, nullable = false)
    private String season;

    @Column(nullable = false)
    private Boolean available;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
