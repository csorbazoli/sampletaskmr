package com.arvoia.sampletask.controller;

import com.arvoia.sampletask.exception.VendorServiceException;
import com.arvoia.sampletask.model.AvailResponse;
import com.arvoia.sampletask.service.AvailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.arvoia.sampletask.constants.AvailServiceConstants;

@Configuration
@RestController
public class AvailRestController {

	private static final Logger LOG = LoggerFactory.getLogger(AvailRestController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AvailService availService;

	@Value("${vendor.url}")
	private String vendorUrl;

	@GetMapping(value = "/avail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  AvailResponse getAvailabilities() {
		AvailResponse ret = new AvailResponse();
		String searchResult = getSearchResults();
		ret.setOffer(availService.getOfferDetails(searchResult));
		ret.setResult(AvailServiceConstants.RESPONSE_SUCCESS);
		return ret;
	}

	private String getSearchResults() {
		LOG.info("AvailRestController::getSearchResults Service Url {}",  vendorUrl);
		try {
			return restTemplate.getForEntity(vendorUrl, String.class).getBody();
		} catch (Exception e) {
			LOG.error("Exception in AvailRestController::getSearchResults {}",e.getMessage());
			throw new VendorServiceException("Vendor Service not available");
		}
	}
}
