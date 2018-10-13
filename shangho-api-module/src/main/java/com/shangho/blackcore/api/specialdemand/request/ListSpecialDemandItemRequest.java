package com.shangho.blackcore.api.specialdemand.request;

import java.util.List;

public class ListSpecialDemandItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = 8796681191636901381L;
	private String token;
	private String status;
	private List<Integer> categories;
	private List<Integer> refers;
	private List<String> names;

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public List<Integer> getCategories() {
		return categories;
	}

	public List<Integer> getRefers() {
		return refers;
	}

	public List<String> getNames() {
		return names;
	}

	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}

	public void setRefers(List<Integer> refers) {
		this.refers = refers;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListSpecialDemandItemRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", refers=");
		builder.append(refers);
		builder.append(", names=");
		builder.append(names);
		builder.append("]");
		return builder.toString();
	}

	public ListSpecialDemandItemRequest(String token, String status, List<Integer> categories, List<Integer> refers,
			List<String> names) {
		super();
		this.token = token;
		this.status = status;
		this.categories = categories;
		this.refers = refers;
		this.names = names;
	}

}
