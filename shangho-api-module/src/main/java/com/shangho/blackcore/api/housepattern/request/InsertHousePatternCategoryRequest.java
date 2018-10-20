package com.shangho.blackcore.api.housepattern.request;

public class InsertHousePatternCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = 1994509155641847332L;
	private String token;
	private String name;
	private String status;
	private int sort;

	public String getToken() {
		return token;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public int getSort() {
		return sort;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsertHousePatternCategoryRequest [token=");
		builder.append(token);
		builder.append(", name=");
		builder.append(name);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sort=");
		builder.append(sort);
		builder.append("]");
		return builder.toString();
	}

	public InsertHousePatternCategoryRequest(String token, String name, String status, int sort) {
		super();
		this.token = token;
		this.name = name;
		this.status = status;
		this.sort = sort;
	}

}
