package net.spring.demo.noXMLconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("net.spring.demo.noXMLConfig")
@PropertySource("classpath:source.properties")
public class FinancialAdvisoryConfig {
    //Define a bean for the BrokerFeeCalculator
    @Bean
    public FeeCalculator brokerFeeCalculator(){
        return new BrokerFeeCalculator();
    }

    //Define the bean for PharmaFinancial Advisor and Inject BrokerFee Calculator in to it
    @Bean
    public FinancialAdvisor pharmaFinancialAdvisor(){
        return new PharmaFinancialAdvisor(brokerFeeCalculator());
    }

}
