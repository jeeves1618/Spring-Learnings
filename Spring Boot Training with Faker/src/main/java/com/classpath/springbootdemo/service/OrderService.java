package com.classpath.springbootdemo.service;

import com.classpath.springbootdemo.model.Order;
import com.classpath.springbootdemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order order){
        log.info("Saving the order :: ", order);
        Order savedOrder = this.orderRepository.save(order);
        return savedOrder;
    }

    public Set<Order> fetchOrders(){
        log.info("fetching all the orders ::");
        Set<Order> orders = new HashSet<>();
        Iterable<Order> iterable = this.orderRepository.findAll();
        iterable.forEach(orders::add);
        return orders;
    }

    public Order fetchOrderByOrderId(long orderId){
        log.info("Fetching the order by id :: {}", orderId);
        return this.orderRepository
                        .findById(orderId)
                        .orElseThrow(() -> new IllegalArgumentException("invalid order id"));
    }

    public void deleteOrderByOrderId(long orderId){
        log.info("Deleting the order by id :: {}", orderId);
        this.orderRepository.deleteById(orderId);
    }
}
