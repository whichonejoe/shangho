package com.shangho.blackcore.api.specialdemand.response;

public class ListSpecialDemandItemResponse implements java.io.Serializable {
	private static final long serialVersionUID = 1951126985808771070L;
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

	public int getCategoryid() {
		return categoryid;
	}

	public int getReferid() {
		return referid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListSpecialDemandItemResponse [id=");
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

	public ListSpecialDemandItemResponse(int id, int categoryid, String categoryname, int referid, String refername,
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
