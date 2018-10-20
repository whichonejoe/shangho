package com.shangho.blackcore.api.housepattern.request;

public class UpdateHousePatternCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = -2722372762654265104L;
	private String token;
	private int id;
	private String name;
	private String status;
	private int sort;

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

	public int getSort() {
		return sort;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateHousePatternCategoryRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sort=");
		builder.append(sort);
		builder.append("]");
		return builder.toString();
	}

	public UpdateHousePatternCategoryRequest(String token, int id, String name, String status, int sort) {
		super();
		this.token = token;
		this.id = id;
		this.name = name;
		this.status = status;
		this.sort = sort;
	}

}
