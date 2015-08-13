package com.almundo.automation.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that defines the airlines on the request parameters using Search
 * service
 * 
 * @author zenen.morales
 *
 */
public class Airline {

	@JsonProperty("catalog_id")
	private String catalogId;

	private String code;

	private String name;

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
