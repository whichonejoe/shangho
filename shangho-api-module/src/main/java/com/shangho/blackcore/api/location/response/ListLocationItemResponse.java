package com.shangho.blackcore.api.location.response;

public class ListLocationItemResponse implements java.io.Serializable {
	private static final long serialVersionUID = -4553237116178937153L;
	private int id;
	private int categoryid;
	private String categoryname;
	private int referid;
	private String refername;
	private String status;
	private String name;
	private String description;

	public int getId() {
		return id;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public int getReferid() {
		return referid;
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
		builder.append("ListLocationItemResponse [id=");
		builder.append(id);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", categoryname=");
		builder.append(categoryname);
		builder.append(", referid=");
		builder.append(referid);
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

	public ListLocationItemResponse(int id, int categoryid, String categoryname, int referid, String refername,
			String status, String name, String description) {
		super();
		this.id = id;
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.referid = referid;
		this.refername = refername;
		this.status = status;
		this.name = name;
		this.description = description;
	}

}
