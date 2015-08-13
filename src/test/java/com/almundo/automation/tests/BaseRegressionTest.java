package com.almundo.automation.tests;

import com.almundo.automation.services.HTTPClient;
import com.almundo.automation.services.ResponseData;
import com.almundo.automation.services.SearchService;
import com.almundo.automation.utils.DataProviders;
import com.almundo.automation.utils.PropertyReader;
import com.almundo.automation.utils.Utils;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.Map;

public class BaseRegressionTest {

	protected HTTPClient httpClient;
	protected PropertyReader data;
	protected DataProviders providers;
	protected Utils utils;

	private SearchService searchService;

	public BaseRegressionTest() {
		this.instanceClient();
		this.instancePropertyReader();
		this.instanceDataProviders();
		this.instanceUtils();
	}

	@Factory()
	@Test(description = "Getting dataproviders", dataProvider = "searchGenericData", dataProviderClass = DataProviders.class)
	public Object[] createInstancesOfFlightSearch(Map<String, String> data) {
		searchService = new SearchService();
		try {
			ResponseData responseData = searchService.retrieveFlights(data);
			return new Object[] { new FlightSearchRegressionTest(responseData, data) };
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void instanceClient() {
		this.httpClient = new HTTPClient();
	}

	private void instancePropertyReader() {
		this.data = new PropertyReader();
	}

	private void instanceDataProviders() {
		this.providers = new DataProviders();
	}

	private void instanceUtils() {
		this.utils = new Utils();
	}

}
