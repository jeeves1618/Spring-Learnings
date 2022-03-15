package com.classpath.springbootdemo.repository;

import com.classpath.springbootdemo.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface OrderRepository extends JpaRepository<Order, Long> {

        Page<Order> findByPriceBetweenOrderByCustomerNameAsc(double min, double max, Pageable pageRequest);

        @Query("select order from Order order where order.email = ?1")
        Optional<Order> findByEmail(String email);

        Optional<Order> findByCustomerNameEndingWith(String lastName);

        List<Order> findOrderByPriceGreaterThan(double minPrice);
}
