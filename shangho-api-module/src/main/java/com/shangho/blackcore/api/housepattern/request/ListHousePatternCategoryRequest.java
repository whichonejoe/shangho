package com.shangho.blackcore.api.housepattern.request;

import java.util.List;

public class ListHousePatternCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = -5378389958298844279L;
	private String token;
	private String status;
	private List<String> names;
	private String sortorderby;

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getNames() {
		return names;
	}

	public String getSortorderby() {
		return sortorderby;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListHousePatternCategoryRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", names=");
		builder.append(names);
		builder.append(", sortorderby=");
		builder.append(sortorderby);
		builder.append("]");
		return builder.toString();
	}

	public ListHousePatternCategoryRequest(String token, String status, List<String> names, String sortorderby) {
		super();
		this.token = token;
		this.status = status;
		this.names = names;
		this.sortorderby = sortorderby;
	}

}
