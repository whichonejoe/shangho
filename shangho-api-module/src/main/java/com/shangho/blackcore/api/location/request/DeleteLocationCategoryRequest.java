package com.shangho.blackcore.api.location.request;

public class DeleteLocationCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = 5503884438323331888L;
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
		builder.append("DeleteLocationCategoryRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public DeleteLocationCategoryRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}
