package com.shangho.blackcore.api.object.request;

import java.util.List;

public class ListObjectCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = -3731885447710226216L;
	private String token;
	private String status;
	private String type;
	private List<String> names;
	private String sortorderby;

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getSortorderby() {
		return sortorderby;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListObjectCategoryRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", type=");
		builder.append(type);
		builder.append(", names=");
		builder.append(names);
		builder.append(", sortorderby=");
		builder.append(sortorderby);
		builder.append("]");
		return builder.toString();
	}

	public ListObjectCategoryRequest(String token, String status, String type, List<String> names, String sortorderby) {
		super();
		this.token = token;
		this.status = status;
		this.type = type;
		this.names = names;
		this.sortorderby = sortorderby;
	}

}
