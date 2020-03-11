package com.arvoia.sampletask.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicles {
	
	@JsonProperty(value="vehicles")
	private List<Vehicle> vehicleList;

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	
	
	
}
