package com.shangho.commom;

public enum ObjectTypeMean {
	SMAILL("small"), BIG("big");

	private final String value;

	private ObjectTypeMean(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
