package com.shangho.blackcore.api.object.request;

public class DeleteObjectCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = 2794964764805983333L;
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
		builder.append("DeleteObjectCategoryRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public DeleteObjectCategoryRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}
