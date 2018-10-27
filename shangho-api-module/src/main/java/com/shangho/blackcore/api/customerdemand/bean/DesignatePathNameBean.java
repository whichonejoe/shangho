package com.shangho.blackcore.api.customerdemand.bean;

public class DesignatePathNameBean implements java.io.Serializable {
	private static final long serialVersionUID = -8748635496819104300L;
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DesignatePathNameBean [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	public DesignatePathNameBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
