package com.classpath.springbootdemo.repository;

import com.classpath.springbootdemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface OrderRepository extends JpaRepository<Order, Long> {
}
