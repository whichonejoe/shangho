package com.shangho.commom;

public enum StatusMean {
	DISABLE("0"), ENABLE("1");

	private final String value;

	private StatusMean(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
