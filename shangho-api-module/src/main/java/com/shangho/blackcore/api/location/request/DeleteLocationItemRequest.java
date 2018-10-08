package com.shangho.blackcore.api.location.request;

public class DeleteLocationItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = 2131917999063634613L;
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
		builder.append("DeleteLocationItemRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public DeleteLocationItemRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}