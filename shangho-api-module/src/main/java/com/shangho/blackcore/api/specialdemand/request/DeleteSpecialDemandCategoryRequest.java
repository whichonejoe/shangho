package com.shangho.blackcore.api.specialdemand.request;

public class DeleteSpecialDemandCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = -1265178982777012765L;
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
		builder.append("DeleteSpecialDemandCategoryRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public DeleteSpecialDemandCategoryRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}
