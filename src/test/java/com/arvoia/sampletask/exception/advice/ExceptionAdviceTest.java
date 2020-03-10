package com.arvoia.sampletask.exception.advice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.arvoia.sampletask.constants.AvailServiceConstants;
import com.arvoia.sampletask.exception.VendorServiceException;
import com.arvoia.sampletask.exeption.advice.ExceptionAdvice;
import com.arvoia.sampletask.model.ErrorResponse;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionAdviceTest {

	@InjectMocks
	private ExceptionAdvice underTest;

	@Before
	public void setupTest() {
		// prepare test
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIllegalAccessException() {
		ResponseEntity<ErrorResponse> responseEntity = underTest
				.handleIllgelAccesException(new IllegalAccessException("Forbidden"));
		assertNotNull(responseEntity);
		ErrorResponse errorResponse = responseEntity.getBody();
		assertNotNull(errorResponse);
		assertEquals(AvailServiceConstants.VENDOR_RESPONSE_FAILURE_CODE, errorResponse.getErrorInfo().getCode());
		assertEquals(AvailServiceConstants.VENDOR_RESPONSE_FAILURE_MSG, errorResponse.getErrorInfo().getDescription());

	}
	
	@Test
	public void testVendorServiceException() {
		ResponseEntity<ErrorResponse> responseEntity = underTest
				.handleVendorServiceException(new VendorServiceException("Not available"));
		assertNotNull(responseEntity);
		ErrorResponse errorResponse = responseEntity.getBody();
		assertNotNull(errorResponse);
		assertEquals(AvailServiceConstants.VENDOR_RESPONSE_FAILURE_CODE, errorResponse.getErrorInfo().getCode());
		assertEquals(AvailServiceConstants.VENDOR_RESPONSE_FAILURE_MSG, errorResponse.getErrorInfo().getDescription());

	}
}
