package com.course.springdata.service;

import com.course.springdata.persistence.entity.Order;
import com.course.springdata.persistence.projection.OrderSummary;
import com.course.springdata.persistence.repository.OrderRepository;
import com.course.springdata.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";

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

    @Secured("ROLE_ADMIN") //Only admin role can execute this method
    public List<Order> getCustomerOrders(String idCustomer){
        return orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getSummery(int orderId){
        return orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto){
        return orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}
