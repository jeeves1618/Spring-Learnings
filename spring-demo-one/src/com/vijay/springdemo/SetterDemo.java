package com.vijay.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemo {

	public static void main(String[] args) {
		
		//Load the Spring Configuration File
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		
		//Retrieve Bean from Spring Container
		Coach theCoach = context.getBean("myCricketCoach",Coach.class);
		
		//Call the methods on the Bean
		System.out.println(theCoach.getDailyWorkout());
		
		//Lets call the dependency
		System.out.println(theCoach.getMonthlyFees());
		
		//Close the Context
		context.close();
	}

}
