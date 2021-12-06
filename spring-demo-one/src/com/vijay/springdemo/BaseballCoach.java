package com.vijay.springdemo;

public class BaseballCoach implements Coach{
	
	//Define an attribute for Fee Service
	private FeeService feeService;
	
	//Constructor for Constructor Dependency Injection
	public BaseballCoach(FeeService feeService) {
		this.feeService = feeService;
	}
	
	public String getDailyWorkout() {
		return "Spend 30 minutes on battting practice";
	}

	@Override
	public String getMonthlyFees() {
		// Use the fee service to get the fees
		return feeService.getFees();
	}
}
