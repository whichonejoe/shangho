package com.shangho.blackcore.api.location.request;

public class UpdateLocationCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = -929646658107802337L;
	private String token;
	private int id;
	private String name;
	private String description;
	private String status;

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
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
		builder.append("UpdateLocationCategoryRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public UpdateLocationCategoryRequest(String token, int id, String name, String description, String status) {
		super();
		this.token = token;
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
	}

}
