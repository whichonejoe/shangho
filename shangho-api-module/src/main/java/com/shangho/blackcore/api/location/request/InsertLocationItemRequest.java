package com.shangho.blackcore.api.location.request;

public class InsertLocationItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = 1571495531122693261L;
	private String token;
	private int categoryid;
	private int referid;
	private String name;
	private String description;
	private String status;

	public String getToken() {
		return token;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public int getReferid() {
		return referid;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsertLocationItemRequest [token=");
		builder.append(token);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", referid=");
		builder.append(referid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public InsertLocationItemRequest(String token, int categoryid, int referid, String name, String description,
			String status) {
		super();
		this.token = token;
		this.categoryid = categoryid;
		this.referid = referid;
		this.name = name;
		this.description = description;
		this.status = status;
	}

}
