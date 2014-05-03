package com.hk.android.sqliteglucose.model;

public class Glucose {
	private int id;
	private String glucose_value;
	
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGlucose_value() {
		return glucose_value;
	}
	public void setGlucose_value(String glucose_value) {
		this.glucose_value = glucose_value;
	}
	
	@Override
	public String toString() {
		
		return glucose_value;
	}
	

}
