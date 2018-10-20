package com.shangho.blackcore.api.designatepath.request;

public class DeleteDesignatePathRequest implements java.io.Serializable {
	private static final long serialVersionUID = 6912271021186446612L;
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
		builder.append("DeleteDesignatePathRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public DeleteDesignatePathRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}
