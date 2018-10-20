package com.shangho.blackcore.api.specialdemand.response;

public class ListSpecialDemandItemResponse implements java.io.Serializable {
	private static final long serialVersionUID = 1951126985808771070L;
	private int id;
	private String categoryname;
	private String refername;
	private String status;
	private String name;
	private String description;

	public int getId() {
		return id;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public String getRefername() {
		return refername;
	}

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ListSpecialDemandItemResponse(int id, String categoryname, String refername, String status, String name,
			String description) {
		super();
		this.id = id;
		this.categoryname = categoryname;
		this.refername = refername;
		this.status = status;
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListSpecialDemandItemResponse [id=");
		builder.append(id);
		builder.append(", categoryname=");
		builder.append(categoryname);
		builder.append(", refername=");
		builder.append(refername);
		builder.append(", status=");
		builder.append(status);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
