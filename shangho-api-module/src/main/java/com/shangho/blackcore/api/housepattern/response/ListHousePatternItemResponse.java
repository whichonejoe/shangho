package com.shangho.blackcore.api.housepattern.response;

public class ListHousePatternItemResponse implements java.io.Serializable {
	private static final long serialVersionUID = -241239656958365743L;
	private int id;
	private int categoryid;
	private String categoryname;
	private String status;
	private String name;
	private int sort;

	public String getCategoryname() {
		return categoryname;
	}

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public int getSort() {
		return sort;
	}

	public int getId() {
		return id;
	}

	public int getCategoryid() {
		return categoryid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListHousePatternItemResponse [id=");
		builder.append(id);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", categoryname=");
		builder.append(categoryname);
		builder.append(", status=");
		builder.append(status);
		builder.append(", name=");
		builder.append(name);
		builder.append(", sort=");
		builder.append(sort);
		builder.append("]");
		return builder.toString();
	}

	public ListHousePatternItemResponse(int id, int categoryid, String categoryname, String status, String name,
			int sort) {
		super();
		this.id = id;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.status = status;
		this.name = name;
		this.sort = sort;
	}

}
