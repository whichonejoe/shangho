package com.shangho.blackcore.api.specialdemand.request;

public class InsertSpecialDemandItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = -7289950709783150685L;
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
		builder.append("InsertSpecialDemandItemRequest [token=");
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

	public InsertSpecialDemandItemRequest(String token, int categoryid, int referid, String name, String description,
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
