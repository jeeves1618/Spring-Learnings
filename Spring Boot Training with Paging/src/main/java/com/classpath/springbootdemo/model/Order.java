package com.classpath.springbootdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private double price;
    private String  customerName;
    private LocalDate date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<LineItem> lineItems;

    public void addLineItem(LineItem lineItem){
        if(this.lineItems == null){
            this.lineItems = new HashSet<>();
        }
        this.getLineItems().add(lineItem);
        lineItem.setOrder(this);
    }
}
