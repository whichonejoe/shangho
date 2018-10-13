package com.shangho.blackcore.api.specialdemand.request;

public class DeleteSpecialDemandItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = 876015158319781429L;
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
		builder.append("DeleteSpecialDemandItemRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public DeleteSpecialDemandItemRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}
