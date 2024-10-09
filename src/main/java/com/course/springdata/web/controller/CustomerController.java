package com.course.springdata.web.controller;

import com.course.springdata.persistence.entity.Customer;
import com.course.springdata.persistence.entity.Order;
import com.course.springdata.service.CustomerService;
import com.course.springdata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Customer> getByPhone(@PathVariable("phone") String phone){
        return ResponseEntity.ok(customerService.findByPhone(phone));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable String id){
        return ResponseEntity.ok(orderService.getCustomerOrders(id));
    }
}
