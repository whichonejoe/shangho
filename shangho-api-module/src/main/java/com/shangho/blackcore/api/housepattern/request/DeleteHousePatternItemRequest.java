package com.shangho.blackcore.api.housepattern.request;

public class DeleteHousePatternItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = 5731699785568154563L;
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

	public DeleteHousePatternItemRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}
