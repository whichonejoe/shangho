package com.shangho.blackcore.api.customerdemand.bean;

public class HousePatternNameBean implements java.io.Serializable {
	private static final long serialVersionUID = -4212662260804606538L;
	private int id;
	private String name;
	private String status;
	private int sort;

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
		builder.append("HousePatternNameBean [id=");
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

	public HousePatternNameBean(int id, String name, String status, int sort) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.sort = sort;
	}

}
