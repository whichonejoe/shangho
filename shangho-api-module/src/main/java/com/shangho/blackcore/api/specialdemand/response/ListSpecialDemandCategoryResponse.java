package com.shangho.blackcore.api.specialdemand.response;

public class ListSpecialDemandCategoryResponse implements java.io.Serializable {
	private static final long serialVersionUID = 7927594283533958788L;
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
		builder.append("ListSpecialDemandCategoryResponse [id=");
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

	public ListSpecialDemandCategoryResponse(int id, String status, String name, String description) {
		super();
		this.id = id;
		this.status = status;
		this.name = name;
		this.description = description;
	}

}
