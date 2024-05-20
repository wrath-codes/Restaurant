package com.wrathcodes.restaurant.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class OrderCustomer extends GenericDomain {

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal orderTotal = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
