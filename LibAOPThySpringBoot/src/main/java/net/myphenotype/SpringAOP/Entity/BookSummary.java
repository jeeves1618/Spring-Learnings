package net.myphenotype.SpringAOP.Entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class BookSummary {
    private int numberOfBooks;
    private double totalCost;
    private String totalCostOfBooks;
}
