package com.wrathcodes.restaurant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Ingredient extends GenericDomain {

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String description;

    // Getters and Setters
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

}