package com.shangho.blackcore.api.specialdemand.request;

public class UpdateSpecialDemandItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = 7275954919603304863L;
	private String token;
	private int id;
	private int categoryid;
	private int referid;
	private String name;
	private String description;
	private String status;

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
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
		builder.append("UpdateSpecialDemandItemRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
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

	public UpdateSpecialDemandItemRequest(String token, int id, int categoryid, int referid, String name, String description,
			String status) {
		super();
		this.token = token;
		this.id = id;
		this.categoryid = categoryid;
		this.referid = referid;
		this.name = name;
		this.description = description;
		this.status = status;
	}

}
