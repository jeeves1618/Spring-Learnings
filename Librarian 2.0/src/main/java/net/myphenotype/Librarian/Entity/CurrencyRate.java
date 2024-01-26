package net.myphenotype.Librarian.Entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Currency_Rate")
public class CurrencyRate {

    @Id
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "rate_of_exchange")
    private Double rateOfExchange;
}
