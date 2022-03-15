package com.classpath.springbootdemo.controller;

import com.classpath.springbootdemo.model.Order;
import com.classpath.springbootdemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public Set<Order> fetchOrders(){
        return this.orderService.fetchOrders();
    }
    @GetMapping("/{id}")
    public Order fetchOrderById(@PathVariable ("id") long id){
        return this.orderService.fetchOrderByOrderId(id);
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order){
        order.getLineItems().forEach(lineItem -> lineItem.setOrder(order));
        return this.orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable("id") long id){
        this.orderService.deleteOrderByOrderId(id);
    }
}
