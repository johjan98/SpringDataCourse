package com.course.springdata.service;

import com.course.springdata.persistence.entity.Order;
import com.course.springdata.persistence.projection.OrderSummary;
import com.course.springdata.persistence.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public List<Order> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return orderRepository.findAllByDateAfter(today);
    }

    public List<Order> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return orderRepository.findAllByMethodIn(methods);
    }

    public List<Order> getCustomerOrders(String idCustomer){
        return orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getSummery(int orderId){
        return orderRepository.findSummary(orderId);
    }
}
