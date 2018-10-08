package com.shangho.blackcore.api.location.request;

import java.util.List;

public class ListLocationCategoryRequest implements java.io.Serializable {
	private static final long serialVersionUID = 2526600160878602467L;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListLocationCategoryRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", names=");
		builder.append(names);
		builder.append("]");
		return builder.toString();
	}

	public ListLocationCategoryRequest(String token, String status, List<String> names) {
		super();
		this.token = token;
		this.status = status;
		this.names = names;
	}
}
