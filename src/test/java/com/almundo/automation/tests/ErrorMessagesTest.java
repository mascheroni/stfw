package com.almundo.automation.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.almundo.automation.services.ResponseData;
import com.almundo.automation.services.SearchService;
import com.almundo.automation.utils.DataProviders;

public class ErrorMessagesTest {
	
	private static final int OLD_DATE_STATUS_CODE = 400;
	private static final String OLD_DATE_ERROR = "you cannot make a search in the past";

	@Test(	description = "Verify the error message to search flights with old dates",
			groups = "error-messages",
			dataProvider = "old_dates",
			dataProviderClass = DataProviders.class)
	public void verify404ErrorWithOldDates(Map<String, String> data) {
		SearchService searchService = new SearchService();
		ResponseData response = searchService.retrieveFlights(data);
		if ( response.getStatusCode() == OLD_DATE_STATUS_CODE ) {
			String actualErrorMessage = response.getErrorMessage();
			if (!actualErrorMessage.contains(OLD_DATE_ERROR))
				Assert.fail("The error message is: " + actualErrorMessage);
		} else {
			throw new SkipException("The response returns a " + response.getStatusCode()
					+ " status code, instead of " + OLD_DATE_STATUS_CODE);
		}
		
	}
}
