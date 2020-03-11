package com.arvoia.sampletask.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.arvoia.sampletask.model.Offer;
import com.arvoia.sampletask.model.Vehicle;
import com.arvoia.sampletask.model.Vehicles;
import com.fasterxml.jackson.core.JsonParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class AvailServiceTest {

	@InjectMocks
	private AvailService underTest;
	
	private static final String JSON_RESPONSE="{\"vehicles\":[{\"brand\":\"Toyota\",\"category\":\"Luxury\",\"basePrice\":1030,\"color\":\"white\",\"doors\":3}]}";
	
	 @Mock
	 private ObjectMapper objectMapper;

	
	@Before
	public void setupTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testOfferDetails() throws JsonParseException, JsonMappingException, IOException {
		
		
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setBrand("Volkswagen");
        vehicle1.setCategory("Economy");
        vehicle1.setBasePrice(2000.0);
        
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Benz");
        vehicle2.setCategory("Premium");
        vehicle2.setBasePrice(200000);
        
        Vehicles vehicles = new Vehicles();
        vehicles.setVehicleList(Arrays.asList(vehicle2,vehicle1));
    
        when(objectMapper.readValue(anyString(), eq(Vehicles.class))).thenReturn(vehicles);
		
		// when
        Offer actual = underTest.getOfferDetails(JSON_RESPONSE);
		// then
		assertEquals(2, actual.getAvailable());
		assertEquals(2000.0, actual.getVehicles().getVehicleList().get(0).getBasePrice(),0);
	}

}
