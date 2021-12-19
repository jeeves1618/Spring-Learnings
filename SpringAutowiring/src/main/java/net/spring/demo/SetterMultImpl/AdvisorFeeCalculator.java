package net.spring.demo.SetterMultImpl;

import org.springframework.stereotype.Component;

@Component
public class AdvisorFeeCalculator implements FeeCalculator {
    @Override
    public String getFee() {
        return "You may want to think about having a finacial advisor. Because, if someone knows how to make money, they will most probably not be advising others...";
    }
}
