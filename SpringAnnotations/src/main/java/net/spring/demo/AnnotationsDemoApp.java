package net.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationsDemoApp {
    public static void main(String[] args) {
        //Read Spring Config File
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //Get the bean from the Spring Context
        FinancialAdvisor techFinancialAdvisor = context.getBean("yourFinancialAdvisor", FinancialAdvisor.class);

        //Call the method on the bean
        System.out.println(techFinancialAdvisor.getDailyStockAdvice());

        //Get the Bean for BankingFinancialAdvisor for the default component name
        //When the explicit Bean ID is not provided in the component, the default without first letter Caps for the class is taken.
        FinancialAdvisor bankingFinancialAdvisor1 = context.getBean("bankingFinancialAdvisor",FinancialAdvisor.class);

        //Call the method for banking Financial Advisor using the default component
        System.out.println(bankingFinancialAdvisor1.getDailyStockAdvice());

        //Get the Bean for BankingFinancialAdvisor for the default component name
        FinancialAdvisor bankingFinancialAdvisor2 = context.getBean("bankingFinancialAdvisor",FinancialAdvisor.class);

        System.out.println("The objects are the same: " + (bankingFinancialAdvisor1 == bankingFinancialAdvisor2));
        //Close the Context
        context.close();
    }
}
