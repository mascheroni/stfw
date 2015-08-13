package com.almundo.automation.tests;

import java.util.Map;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.almundo.automation.services.ResponseData;
import com.almundo.automation.services.SearchService;
import com.almundo.automation.utils.DataProviders;

public class TestFactory {

	private SearchService searchService;
	
	@Factory()
	@Test(description = "Getting dataproviders", dataProvider = "suite", dataProviderClass = DataProviders.class)
	public Object[] createSuite(Map<String, String> data) {
		searchService = new SearchService();
		try {
			ResponseData responseData = searchService.retrieveFlights(data);
			return new Object[] { 
					new FlightSearchTests(responseData, data)
					};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
