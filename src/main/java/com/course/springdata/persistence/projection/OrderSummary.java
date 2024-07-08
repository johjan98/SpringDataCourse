package com.course.springdata.persistence.projection;

import java.time.LocalDateTime;

/**
 * This interface contains the attributes that we will obtain using a personalized query
 */
public interface OrderSummary {
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
