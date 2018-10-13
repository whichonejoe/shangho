package com.shangho.blackcore.api.specialdemand.request;

public class InsertSpecialDemandCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = 5086219263021844761L;
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
		builder.append("InsertSpecialDemandCategoryRequest [token=");
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

	public InsertSpecialDemandCategoryRequest(String token, String name, String description, String status) {
		super();
		this.token = token;
		this.name = name;
		this.description = description;
		this.status = status;
	}

}
