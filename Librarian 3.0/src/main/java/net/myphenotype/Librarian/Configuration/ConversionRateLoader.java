package net.myphenotype.Librarian.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.Entity.CurrencyRate;
import net.myphenotype.Librarian.Repository.CurrencyRateRepository;
import net.myphenotype.Librarian.Service.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ConversionRateLoader implements ApplicationListener<ApplicationReadyEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Autowired
    CurrencyRate currencyRate;

    @Autowired
    CurrencyRateRepository currencyRateRepository;

    @Value("${currency.converter.endpoint}")
    private String CURRENCY_CONVERTER_ENDPOINT;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<String> currencyCodes = new ArrayList<>();
        currencyCodes.add("USD");
        currencyCodes.add("CAD");
        currencyCodes.add("GBP");
        currencyCodes.add("EUR");
        currencyCodes.add("SGD");
        currencyCodes.add("ISK");

        for(String currencyCode: currencyCodes) {

            try {
                double rate = Converter.amountInRupee(CURRENCY_CONVERTER_ENDPOINT, currencyCode, 1);
                currencyRate.setCurrencyCode(currencyCode);
                currencyRate.setRateOfExchange(rate);
                try {
                    CurrencyRate savedRate = currencyRateRepository.findById(currencyCode).get();
                    if (savedRate == null) {
                        log.info("Rate not found in the converter table");
                        currencyRateRepository.save(currencyRate);
                    } else {
                        log.info("Conversion record for " + currencyCode + " available. Refreshing it with more recent rate.");
                        currencyRateRepository.deleteById(currencyCode);
                        currencyRateRepository.save(currencyRate);
                    }
                } catch (NoSuchElementException e) {
                    log.info("Rate not found in the converter table : " + e.getMessage());
                    currencyRateRepository.save(currencyRate);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
