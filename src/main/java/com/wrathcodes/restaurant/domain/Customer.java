package com.wrathcodes.restaurant.domain;

import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Customer extends GenericDomain {

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private RestaurantTable seatedAt;
    
    @Column(nullable = false)
    private Boolean active = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public RestaurantTable getSeatedAt() {
		return seatedAt;
	}
	
	public void setSeatedAt(RestaurantTable seatedAt) {
		this.seatedAt = seatedAt;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
}
