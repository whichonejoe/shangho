package com.shangho.blackcore.api.housepattern.request;

public class DeleteHousePatternCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = 994143725752354033L;
	private String token;
	private int id;

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeleteHousePatternCategoryRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public DeleteHousePatternCategoryRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}
