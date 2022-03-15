package com.classpath.springbootdemo.config;

import com.classpath.springbootdemo.model.LineItem;
import com.classpath.springbootdemo.model.Order;
import com.classpath.springbootdemo.repository.OrderRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BootstrapApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final OrderRepository orderRepository;
    private final Faker faker = new Faker();

    @Value("${app.ordercount}")
    private int orderCount;

    @Value("${app.lineitemcount}")
    private int lineItemCount;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
      log.info("==========Application is ready ===================");
        IntStream.range(0,orderCount).forEach((index) -> {
            LocalDate orderDate = faker.date().past(4, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Order order = Order.builder()
                                    .date(orderDate)
                                    .price(faker.number().randomDouble(2, 2_000, 8_000))
                                    .customerName(faker.name().fullName())
                                .build();
            IntStream.range(0, faker.number().numberBetween(2,lineItemCount))
                    .forEach(value -> {
                            LineItem lineItem = LineItem.builder()
                                                            .name(faker.commerce().productName())
                                                            .price(faker.number().randomDouble(2, 500, 800))
                                                            .qty(faker.number().numberBetween(2,6))
                                                        .build();
                           order.addLineItem(lineItem);
            });
            this.orderRepository.save(order);
        });
      log.info("==========Application is ready ===================");
    }
}
