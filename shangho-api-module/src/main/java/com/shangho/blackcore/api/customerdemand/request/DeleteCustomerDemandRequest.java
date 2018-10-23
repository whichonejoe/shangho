package com.shangho.blackcore.api.customerdemand.request;

public class DeleteCustomerDemandRequest implements java.io.Serializable {
	private static final long serialVersionUID = -1016556769237485436L;
	private String token;
	private int id;

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeleteCustomerDemandRequest [token=");
		builder.append(token);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public DeleteCustomerDemandRequest(String token, int id) {
		super();
		this.token = token;
		this.id = id;
	}

}
