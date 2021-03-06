package net.spring.demo.noXMLconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationsDemoApp {
    public static void main(String[] args) {
        //Read Spring Config Java Class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FinancialAdvisoryConfig.class);

        //Get the bean from the Spring Context
        FinancialAdvisor techFinancialAdvisor = context.getBean("pharmaFinancialAdvisor", FinancialAdvisor.class);

        //Call the method on the bean
        System.out.println(techFinancialAdvisor.getDailyStockAdvice());
        System.out.println(techFinancialAdvisor.getDailyFeeQuote());

        //Get the Bean for BankingFinancialAdvisor for the default component name
        //When the explicit Bean ID is not provided in the component, the default without first letter Caps for the class is taken.
        FinancialAdvisor bankingFinancialAdvisor = context.getBean("bankingFinancialAdvisor", FinancialAdvisor.class);

        //Call the method for banking Financial Advisor using the default component
        System.out.println(bankingFinancialAdvisor.getDailyStockAdvice());
        System.out.println(bankingFinancialAdvisor.getDailyFeeQuote());

        //Close the Context
        context.close();
    }
}
