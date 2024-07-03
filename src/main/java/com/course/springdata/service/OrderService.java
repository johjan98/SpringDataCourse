package com.course.springdata.service;

import com.course.springdata.persistence.entity.Order;
import com.course.springdata.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }
}
