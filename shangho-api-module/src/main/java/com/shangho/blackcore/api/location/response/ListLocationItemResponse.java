package com.shangho.blackcore.api.location.response;

public class ListLocationItemResponse implements java.io.Serializable {
	private static final long serialVersionUID = -4553237116178937153L;
	private String categoryname;
	private String refername;
	private String status;
	private String name;
	private String description;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListLocationItemResponse [categoryname=");
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

	public ListLocationItemResponse(String categoryname, String refername, String status, String name,
			String description) {
		super();
		this.categoryname = categoryname;
		this.refername = refername;
		this.status = status;
		this.name = name;
		this.description = description;
	}
}
