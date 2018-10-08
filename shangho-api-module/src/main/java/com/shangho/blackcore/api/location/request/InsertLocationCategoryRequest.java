package com.shangho.blackcore.api.location.request;

public class InsertLocationCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = 8333318002343104738L;
	private String token;
	private String name;
	private String description;
	private String status;

	public String getToken() {
		return token;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsertLocationCategoryRequest [token=");
		builder.append(token);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public InsertLocationCategoryRequest(String token, String name, String description, String status) {
		super();
		this.token = token;
		this.name = name;
		this.description = description;
		this.status = status;
	}

}
