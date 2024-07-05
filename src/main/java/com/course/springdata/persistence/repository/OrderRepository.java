package com.course.springdata.persistence.repository;

import com.course.springdata.persistence.entity.Order;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {
    List<Order> findAllByDateAfter(LocalDateTime date);
    List<Order> findAllByMethodIn(List<String> methods);
}
