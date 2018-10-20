package com.shangho.blackcore.api.housepattern.request;

public class InsertHousePatternItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = 3109668915247815307L;
	private String token;
	private int categoryid;
	private String name;
	private String status;
	private int sort;

	public String getToken() {
		return token;
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
		builder.append("InsertHousePatternItemRequest [token=");
		builder.append(token);
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

	public InsertHousePatternItemRequest(String token, int categoryid, String name, String status, int sort) {
		super();
		this.token = token;
		this.categoryid = categoryid;
		this.name = name;
		this.status = status;
		this.sort = sort;
	}

}
