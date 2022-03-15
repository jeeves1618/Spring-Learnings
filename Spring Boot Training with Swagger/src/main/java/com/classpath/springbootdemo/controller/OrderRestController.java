package com.classpath.springbootdemo.controller;

import com.classpath.springbootdemo.model.Order;
import com.classpath.springbootdemo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    @Operation(method = "fetchAllOrders", description = "API to fetch all the orders")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "fetch all the orders"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    public Map<String, Object> fetchOrders(
            @RequestParam(name = "page",required = false, defaultValue = "0" ) int page,
            @RequestParam(name = "size",required = false, defaultValue = "10" ) int size,
            @RequestParam(name = "direction",required = false, defaultValue = "asc" ) String direction,
            @RequestParam(name = "property",required = false, defaultValue = "customerName" ) String property
    ){

        return this.orderService.fetchOrders(page, size, direction, property);
    }
 @GetMapping("/price")
    public Map<String, Object> fetchOrdersByPrice(
             @RequestParam(name = "min") double min,
             @RequestParam(name = "max") double max,
             @RequestParam(name = "page",required = false, defaultValue = "0" ) int page,
             @RequestParam(name = "size",required = false, defaultValue = "10" ) int size
    ){

        return this.orderService.findByPriceAndSortByCustomerName(page, size, min, max);
    }

    @GetMapping("/{id}")
    public Order fetchOrderById(@PathVariable ("id") long id){
        return this.orderService.fetchOrderByOrderId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveOrder(@Valid @RequestBody Order order){
        order.getLineItems().forEach(lineItem -> lineItem.setOrder(order));
        return this.orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable("id") long id){
        this.orderService.deleteOrderByOrderId(id);
    }
}
