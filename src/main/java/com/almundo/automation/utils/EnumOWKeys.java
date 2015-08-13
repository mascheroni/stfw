package com.almundo.automation.utils;

public enum EnumOWKeys {
	SITE("site"), TYPE("type"), FROM("from"), TO("to"), DEPARTURE("departure"), ADULTS(
			"adults"), CHILDREN("children"), INFANTS("infants"), LANGUAGE(
			"language");

	private String value;

	EnumOWKeys(final String value) {
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
