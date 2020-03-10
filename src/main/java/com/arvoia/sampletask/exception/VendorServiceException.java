package com.arvoia.sampletask.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VendorServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(VendorServiceException.class);

	public VendorServiceException(String message) {
		super(message);
		LOG.error("VendorServiceException " + message);
	}

}
