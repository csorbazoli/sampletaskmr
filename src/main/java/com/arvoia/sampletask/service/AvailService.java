package com.arvoia.sampletask.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arvoia.sampletask.model.Vehicle;
import com.arvoia.sampletask.model.Vehicles;
import com.arvoia.sampletask.controller.AvailRestController;
import com.arvoia.sampletask.exception.VendorServiceException;
import com.arvoia.sampletask.model.Offer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AvailService {

	private static final Logger LOG = LoggerFactory.getLogger(AvailRestController.class);

	@Autowired
	private ObjectMapper objectMapper;

	public Offer getOfferDetails(String vehiclesResponse) {
		List<Vehicle> vehicleList = new ArrayList<>();
		if (vehiclesResponse != null) {
			try {
				vehicleList = ((Vehicles) objectMapper.readValue(vehiclesResponse, Vehicles.class)).getVehicleList();
			} catch (IOException e) {
				LOG.error("Exception in AvailService::getOfferDetails {}", e.getMessage());
				throw new VendorServiceException("Unexpected response format");
			}
		}
		LOG.info("Vehicle data " + vehicleList);
		return constructOfferInfo(vehicleList);
	}

	private Offer constructOfferInfo(List<Vehicle> vehicleList) {
		Offer offer = new Offer();
		offer.setAvailable(vehicleList.size());
		offer.setTimestamp(LocalDateTime.now());
		List<Vehicle> sortedVehicles = vehicleList.stream().sorted(Comparator.comparing(Vehicle::getBasePrice))
				.collect(Collectors.toList());
		Vehicles vehicles = new Vehicles();
		vehicles.setVehicleList(sortedVehicles);
		offer.setVehicles(vehicles);
		return offer;
	}
}
