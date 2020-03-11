package com.arvoia.sampletask.model;

public class Vehicle {

	private String brand;
	private String category;
	private double basePrice;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public String toString() {
		return "Vehicle [brand=" + brand + ", category=" + category + ", basePrice=" + basePrice + "]";
	}
	
	

}
