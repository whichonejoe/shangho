package com.shangho.blackcore.api.customerdemand.bean;

public class SpecialDemandNameBean implements java.io.Serializable {
	private static final long serialVersionUID = -7396271900636386852L;
	private int id;
	private String name;
	private String status;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpecialDemandNameBean [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

	public SpecialDemandNameBean(int id, String name, String status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}

}
