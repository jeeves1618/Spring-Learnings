package com.vijay.springdemo;

public class TrackCoach implements Coach {
	
	private FeeService feeService;
	
	public TrackCoach() {
		
	}
	
	public TrackCoach(FeeService feeService) {
		super();
		this.feeService = feeService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5000 meter everyday";
	}

	@Override
	public String getMonthlyFees() {
		// TODO Auto-generated method stub
		return feeService.getFees();
	}

}
