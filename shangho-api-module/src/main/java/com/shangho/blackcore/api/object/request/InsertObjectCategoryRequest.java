package com.shangho.blackcore.api.object.request;

public class InsertObjectCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = -2807070477159902987L;
	private String token;
	private String name;
	private String status;
	private String type;
	private int sort;
	private String description;

	public String getToken() {
		return token;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public int getSort() {
		return sort;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsertObjectCategoryRequest [token=");
		builder.append(token);
		builder.append(", name=");
		builder.append(name);
		builder.append(", status=");
		builder.append(status);
		builder.append(", type=");
		builder.append(type);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public InsertObjectCategoryRequest(String token, String name, String status, String type, int sort,
			String description) {
		super();
		this.token = token;
		this.name = name;
		this.status = status;
		this.type = type;
		this.sort = sort;
		this.description = description;
	}

}
