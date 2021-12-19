package com.vijay.springdemo;

public class CricketCoach implements Coach {
	
	private FeeService feeService;
	
	public void setFeeService(FeeService feeService) {
		System.out.println("Inside the Setter of cricket coach");
		this.feeService = feeService;
	}

	
	public CricketCoach() {
		System.out.println("Inside the no argument constructor of cricket coach");
	}
	
	@Override
	public String getDailyWorkout() {
		return "Practice cover drive on the rise for 45 minutes";
	}

	@Override
	public String getMonthlyFees() {
		return feeService.getFees();
	}

}
