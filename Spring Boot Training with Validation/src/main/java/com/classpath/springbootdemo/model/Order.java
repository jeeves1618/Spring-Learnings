package com.classpath.springbootdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 5000, message = "Min order price should be 5000")
    @Max(value = 50000, message = "Max order price should be 50000")
    private double price;

    @NotEmpty(message = "Customer name cannot be empty")
    private String  customerName;

    @Email(message = "email is not in correct format")
    private String email;

    @PastOrPresent(message = "order data cannot be in the future")
    private LocalDate date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LineItem> lineItems;

    public void addLineItem(LineItem lineItem){
        if(this.lineItems == null){
            this.lineItems = new HashSet<>();
        }
        this.getLineItems().add(lineItem);
        lineItem.setOrder(this);
    }
}
