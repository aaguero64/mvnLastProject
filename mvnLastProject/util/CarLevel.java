package com.sierraclass.mvnLastProject.util;

public enum CarLevel {
	
	ECONOMIC(.05),
	MEDIUM(.10),
	SPORT(.15),
	DELUX(.20);
	
	private double discount;
	
	CarLevel(double discount){
		this.discount = discount;
	}
	public double getDiscount() {
		return discount;
	}

}
