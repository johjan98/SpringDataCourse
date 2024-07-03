package com.course.springdata.persistence.repository;

import com.course.springdata.persistence.entity.Order;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {

}
