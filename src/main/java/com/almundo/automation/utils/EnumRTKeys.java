package com.almundo.automation.utils;

public enum EnumRTKeys {
	SITE("site"), TYPE("type"), FROM("from"), TO("to"), DEPARTURE_DATE(
			"departure_date"), ARRIVAL_DATE("arrival_date"), ADULTS("adults"), CHILDREN(
			"children"), INFANTS("infants"), LANGUAGE("language");

	private String value;

	EnumRTKeys(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
