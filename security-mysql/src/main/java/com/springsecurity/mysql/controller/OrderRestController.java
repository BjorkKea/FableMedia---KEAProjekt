package com.springsecurity.mysql.controller;

import com.springsecurity.mysql.domains.Order;
import com.springsecurity.mysql.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(path="/orders", method=RequestMethod.GET)
    public List<Order> getAllEmployees(){
        return orderService.getAllOrders();
    }
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public Order getEmployeeById(@PathVariable("id") int id){
        Optional<Order> order = orderService.getOrderByDomainId(id);
        if(order.isPresent())
            return order.get();
        else return null;
    }

}
