package com.almundo.automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDataFactory {

	private static FileDataFactory fileDataFactory = new FileDataFactory();

	public static FileDataFactory getInstance() throws IOException {
		return fileDataFactory;
	}

	public Object[][] getDataSet(String path) throws Exception {
		return this.readFile(path);
	}

	public List<String> getAllLines(String path) throws IOException {
		File dir = new File(path);
		List<String> lines = Files.readAllLines(
				Paths.get(dir.getAbsolutePath()), Charset.defaultCharset());
		return lines;
	}

	private Object[][] readFile(String path) throws Exception {
		Object[][] dataSet;
		List<String> lines = this.getAllLines(path);
		dataSet = new Object[lines.size()][1];
		int i = 0;
		for (String line : lines) {
			String[] parameters = line.split("/");
			Map<String, String> dataObjectsHashMap = new HashMap<String, String>();
			if ("RT".equals(parameters[1])) {
				dataObjectsHashMap = this.generateMap(parameters,
						EnumRTKeys.values());
			} else if ("OW".equals(parameters[1])) {
				dataObjectsHashMap = this.generateMap(parameters,
						EnumOWKeys.values());
			} else if ("MD".equals(parameters[1])) {
				dataObjectsHashMap = this.generateMap(parameters,
						EnumOWKeys.values());
			}
			dataSet[i][0] = this.changeKeys(dataObjectsHashMap);
			i++;
		}
		return dataSet;
	}

	public Map<String, String> generateMap(String[] parameters,
			@SuppressWarnings("rawtypes") Enum[] keys) throws Exception {
		Map<String, String> dataObjectsHashMap = new HashMap<String, String>();
		if (parameters.length == keys.length) {
			for (int i = 0; i < parameters.length; i++) {
				dataObjectsHashMap.put(keys[i].toString(), parameters[i]);
			}
		} else
			throw new Exception();
		return dataObjectsHashMap;
	}

	private Map<String, String> changeKeys(
			Map<String, String> dataObjectsHashMap) {
		Utils utils = new Utils();
		if (dataObjectsHashMap.get(EnumRTKeys.TYPE.toString()).equals("RT")) {
			String from = dataObjectsHashMap.get(EnumRTKeys.FROM.toString());
			String to = dataObjectsHashMap.get(EnumRTKeys.TO.toString());
			dataObjectsHashMap.put(EnumRTKeys.FROM.toString(), from + "," + to);
			dataObjectsHashMap.put(EnumRTKeys.TO.toString(), to + "," + from);

			String stringDepartureDate = utils
					.convertToSpecifDate(dataObjectsHashMap
							.get(EnumRTKeys.DEPARTURE_DATE.toString()));
			String stringArrivalDate = utils
					.convertToSpecifDate(dataObjectsHashMap
							.get(EnumRTKeys.ARRIVAL_DATE.toString()));
			dataObjectsHashMap.remove(EnumRTKeys.DEPARTURE_DATE.toString());
			dataObjectsHashMap.remove(EnumRTKeys.ARRIVAL_DATE.toString());
			dataObjectsHashMap.put(EnumOWKeys.DEPARTURE.toString(),
					stringDepartureDate + "," + stringArrivalDate);
		} else {
			String stringDate = utils.convertToSpecifDate(dataObjectsHashMap
					.get(EnumOWKeys.DEPARTURE.toString()));
			dataObjectsHashMap.put(EnumOWKeys.DEPARTURE.toString(), stringDate);
		}
		dataObjectsHashMap.remove(EnumOWKeys.TYPE.toString());
		return dataObjectsHashMap;
	}
}
