package com.shangho.blackcore.api.housepattern.response;

public class ListHousePatternCategoryResponse implements java.io.Serializable {
	private static final long serialVersionUID = 5954446324257872864L;
	private int id;
	private String status;
	private int sort;
	private String name;

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public int getSort() {
		return sort;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListHousePatternCategoryResponse [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	public ListHousePatternCategoryResponse(int id, String status, int sort, String name) {
		super();
		this.id = id;
		this.status = status;
		this.sort = sort;
		this.name = name;
	}

}
