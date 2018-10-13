package com.shangho.blackcore.api.specialdemand.request;

import java.util.List;

public class ListSpecialDemandCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = 3929926236958928696L;
	private String token;
	private String status;
	private List<String> names;

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListSpecialDemandCategoryRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", names=");
		builder.append(names);
		builder.append("]");
		return builder.toString();
	}

	public ListSpecialDemandCategoryRequest(String token, String status, List<String> names) {
		super();
		this.token = token;
		this.status = status;
		this.names = names;
	}
}
