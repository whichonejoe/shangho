package com.shangho.blackcore.api.housepattern.request;

import java.util.List;

public class ListHousePatternItemRequest implements java.io.Serializable {
	private static final long serialVersionUID = 2700386461377929103L;
	private String token;
	private String status;
	private List<Integer> categories;
	private List<String> names;
	private String sortorderby;

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public List<Integer> getCategories() {
		return categories;
	}

	public List<String> getNames() {
		return names;
	}

	public String getSortorderby() {
		return sortorderby;
	}

	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListHousePatternItemRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", names=");
		builder.append(names);
		builder.append(", sortorderby=");
		builder.append(sortorderby);
		builder.append("]");
		return builder.toString();
	}

	public ListHousePatternItemRequest(String token, String status, List<Integer> categories, List<String> names,
			String sortorderby) {
		super();
		this.token = token;
		this.status = status;
		this.categories = categories;
		this.names = names;
		this.sortorderby = sortorderby;
	}

}
