package com.shangho.blackcore.api.housepattern.request;

public class UpdateHousePatternItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = -6428125793043870487L;
	private String token;
	private int id;
	private int categoryid;
	private String name;
	private String status;
	private int sort;

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public int getCategoryid() {
		return categoryid;
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
		builder.append("UpdateHousePatternItemRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sort=");
		builder.append(sort);
		builder.append("]");
		return builder.toString();
	}

	public UpdateHousePatternItemRequest(String token, int id, int categoryid, String name, String status, int sort) {
		super();
		this.token = token;
		this.id = id;
		this.categoryid = categoryid;
		this.name = name;
		this.status = status;
		this.sort = sort;
	}

}
