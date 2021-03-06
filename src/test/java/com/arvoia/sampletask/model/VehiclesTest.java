/** This class was generated by GenTest@Mobacar */
package com.arvoia.sampletask.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VehiclesTest {

	/**
	 * If the class has a default constructor, then you don't need to instantiate it
	 * manually. InjectMocks annotation will do it.<br/>
	 * Otherwise use the {@link #setupTest()} method for creating the underTest
	 * object.
	 */
	@InjectMocks
	private Vehicles underTest;

	@Before
	public void setupTest() {
		// prepare test
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetVehicleList() {
		// given
		Vehicle vehicle = Mockito.mock(Vehicle.class);
		List<Vehicle> value = Arrays.asList(vehicle);
		underTest.setVehicleList(value);
		// when
		List<Vehicle> actual = underTest.getVehicleList();
		// then
		assertEquals(value, actual);
	}

}
