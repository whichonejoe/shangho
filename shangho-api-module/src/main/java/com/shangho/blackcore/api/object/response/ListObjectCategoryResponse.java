package com.shangho.blackcore.api.object.response;

public class ListObjectCategoryResponse implements java.io.Serializable {
	private static final long serialVersionUID = 4214841707449739484L;
	private String status;
	private String type;
	private int sort;
	private String name;
	private String description;

	public String getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public int getSort() {
		return sort;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListLocationCategoryResponse [status=");
		builder.append(status);
		builder.append(", type=");
		builder.append(type);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public ListObjectCategoryResponse(String status, String type, int sort, String name, String description) {
		super();
		this.status = status;
		this.type = type;
		this.sort = sort;
		this.name = name;
		this.description = description;
	}

}