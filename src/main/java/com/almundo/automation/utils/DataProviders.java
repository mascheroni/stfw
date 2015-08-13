package com.almundo.automation.utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	private static final String RESOURCE_PATH = "com/almundo/automation/data";
	private static final String GENERIC_FILE = "searchGenericData.file";
	private static final String OLD_DATES_FILE = "old_dates.file";

	public DataProviders() {
	}
	
	//Deprecated
	@DataProvider(name = "searchGenericData")
	public static Object[][] getSearchGenericData() throws IOException {
		XmlDataFactory factory = initializeXmlDataFactory();
		return factory.getArrayFromDataSet("searchGenericData");
	}
	
	//Deprecated
	private static XmlDataFactory initializeXmlDataFactory() throws IOException {
		XmlDataFactory factory = XmlDataFactory.getInstance();
		String path = DataProviders.class.getClassLoader()
				.getResource("com/almundo/automation/data").getPath();
		factory.initialize(path + "/");
		return factory;
	}
	
	//Deprecated
	@DataProvider(name = "searchGenericDataFromFile")
	public static Object[][] searchGenericDataFromFile() throws Exception {
		FileDataFactory fileDataFactory = FileDataFactory.getInstance();
		String path = DataProviders.class.getClassLoader()
				.getResource("com/almundo/automation/data").getPath();
		return fileDataFactory.getDataSet(path + "/" + GENERIC_FILE);
	}
	
	@DataProvider(name = "suite")
	public static Object[][] suiteData() throws Exception {
		FileDataFactory fileDataFactory = FileDataFactory.getInstance();
		String path = DataProviders.class.getClassLoader()
				.getResource(RESOURCE_PATH).getPath();
		return fileDataFactory.getDataSet(path + "/" 
				+ System.getProperty("suite") + ".file");
	}
	
	@DataProvider(name = "old_dates")
	public static Object[][] getSearchOldDates() throws Exception {
		FileDataFactory fileDataFactory = FileDataFactory.getInstance();
		String path = DataProviders.class.getClassLoader()
				.getResource(RESOURCE_PATH).getPath();
		return fileDataFactory.getDataSet(path + "/" + OLD_DATES_FILE);
	}

}
