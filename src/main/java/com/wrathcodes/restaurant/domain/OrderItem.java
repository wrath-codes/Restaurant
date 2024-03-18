package com.wrathcodes.restaurant.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class OrderItem extends GenericDomain {
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private OrderPriority priority;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private MenuItem item;

    @ManyToOne
    @JoinColumn(nullable = false)
    private OrderCustomer orderCustomer;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderPriority getPriority() {
        return priority;
    }

    public void setPriority(OrderPriority priority) {
        this.priority = priority;
    }

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public OrderCustomer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(OrderCustomer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

}