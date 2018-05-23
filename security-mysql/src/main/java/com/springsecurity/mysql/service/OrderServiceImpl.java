package com.springsecurity.mysql.service;

import java.util.List;
import java.util.Optional;


import com.springsecurity.mysql.domains.Order;
import com.springsecurity.mysql.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.mysql.repository.OrderRepository;

@Service("orderService")
public class OrderServiceImpl implements OrderService  {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderByDomainId(int domainId) {
        return orderRepository.findById( domainId);
    }

}
