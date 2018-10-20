package com.shangho.blackcore.api.location.response;

public class ListLocationCategoryResponse implements java.io.Serializable {
	private static final long serialVersionUID = 7993004047475450117L;
	private int id;
	private String status;
	private String name;
	private String description;

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListLocationCategoryResponse [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public ListLocationCategoryResponse(int id, String status, String name, String description) {
		super();
		this.id = id;
		this.status = status;
		this.name = name;
		this.description = description;
	}

}
