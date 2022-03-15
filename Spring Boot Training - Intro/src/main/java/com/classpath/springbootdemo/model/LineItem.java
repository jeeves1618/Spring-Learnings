package com.classpath.springbootdemo.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "order")
@ToString(exclude = "order")
@Builder
@AllArgsConstructor
@Entity
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "item_name")
    private String name;
    private int qty;
    private double price;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;
}
