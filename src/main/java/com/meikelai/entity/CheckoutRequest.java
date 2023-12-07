package com.meikelai.entity;

import java.util.List;

public class CheckoutRequest {
    private Long userId;
    private List<OrderDetail> details;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}
