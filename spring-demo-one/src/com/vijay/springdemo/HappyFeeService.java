package com.vijay.springdemo;

public class HappyFeeService implements FeeService {

	@Override
	public String getFees() {
		return "Your fees for this month is $43,510";
	}

}
