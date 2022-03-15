package com.classpath.springbootdemo.service;

import com.classpath.springbootdemo.model.Order;
import com.classpath.springbootdemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<String, Object> fetchOrders(int page, int size, String direction, String property){
        log.info("fetching all the orders ::");
        /*Set<Order> orders = new HashSet<>();
        Iterable<Order> iterable = this.orderRepository.findAll();
        iterable.forEach(orders::add);*/
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest pageRequest = PageRequest.of(page, size, sortDirection, property);
        Page<Order> response = this.orderRepository.findAll(pageRequest);

        long totalRecords = response.getTotalElements();
        int totalPages = response.getTotalPages();
        List<Order> orders = response.getContent();
        int number = response.getNumber();
        int sizePerPage = response.getSize();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("total-records", totalRecords);
        resultMap.put("total-pages", totalPages);
        resultMap.put("page", number);
        resultMap.put("records", sizePerPage);
        resultMap.put("data", orders);

        return resultMap;
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

    public Map<String, Object> findByPriceAndSortByCustomerName(int page, int size, double min, double max) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "customerName");
        Page<Order> response = this.orderRepository.findByPriceBetweenOrderByCustomerNameAsc(min, max, pageRequest);

        long totalRecords = response.getTotalElements();
        int totalPages = response.getTotalPages();
        List<Order> orders = response.getContent();
        int number = response.getNumber();
        int sizePerPage = response.getSize();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("total-records", totalRecords);
        resultMap.put("total-pages", totalPages);
        resultMap.put("page", number);
        resultMap.put("records", sizePerPage);
        resultMap.put("data", orders);

        return resultMap;
    }
}
