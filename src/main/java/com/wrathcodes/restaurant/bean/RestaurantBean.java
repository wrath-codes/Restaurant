package com.wrathcodes.restaurant.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class RestaurantBean {
    RestaurantBean restaurantBean;

    public void save() {
        System.out.println("I'm on bean Restaurant");
    }
}
