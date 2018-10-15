package com.shangho.blackcore.api.object.request;

public class UpdateObjectCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = -1670190270926545605L;
	private String token;
	private int id;
	private String name;
	private String status;
	private String type;
	private int sort;
	private String description;

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
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
		builder.append("UpdateObjectCategoryRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
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

	public UpdateObjectCategoryRequest(String token, int id, String name, String status, String type, int sort,
			String description) {
		super();
		this.token = token;
		this.id = id;
		this.name = name;
		this.status = status;
		this.type = type;
		this.sort = sort;
		this.description = description;
	}

}
