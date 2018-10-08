package com.shangho.blackcore.api.request;

public class APIRequest implements java.io.Serializable {
	private static final long serialVersionUID = -8822530494794990907L;
	private Object content;
	private int page = 1;
	private int limit = 10;

	public APIRequest(Object content) {
		super();
		this.content = content;
	}

	public APIRequest(Object content, int page, int limit) {
		super();
		this.content = content;
		this.page = page;
		this.limit = limit;
	}

	public Object getContent() {
		return content;
	}

	public int getPage() {
		return page;
	}

	public int getLimit() {
		return limit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIRequest [content=\n");
		builder.append(content);
		builder.append(", page=\n");
		builder.append(page);
		builder.append(", limit=\n");
		builder.append(limit);
		builder.append("]");
		return builder.toString();
	}

}

