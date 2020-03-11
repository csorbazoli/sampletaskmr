package com.arvoia.sampletask.exeption.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.arvoia.sampletask.constants.AvailServiceConstants;
import com.arvoia.sampletask.exception.VendorServiceException;
import com.arvoia.sampletask.model.ErrorInfo;
import com.arvoia.sampletask.model.ErrorResponse;

@Controller
@RestControllerAdvice
public class ExceptionAdvice {

	private static ErrorResponse errorResponse;
	//Mapping it in static block as we need to throw same error details
	static {
		errorResponse = new ErrorResponse();
		errorResponse.setResult(AvailServiceConstants.RESPONSE_FAILURE);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setCode(AvailServiceConstants.VENDOR_RESPONSE_FAILURE_CODE);
		errorInfo.setDescription(AvailServiceConstants.VENDOR_RESPONSE_FAILURE_MSG);
		errorResponse.setErrorInfo(errorInfo);
	}

	@ExceptionHandler(value = IllegalAccessException.class)
	public ResponseEntity<ErrorResponse> handleIllgelAccesException(IllegalAccessException e) {
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = VendorServiceException.class)
	public ResponseEntity<ErrorResponse> handleVendorServiceException(VendorServiceException e) {
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
