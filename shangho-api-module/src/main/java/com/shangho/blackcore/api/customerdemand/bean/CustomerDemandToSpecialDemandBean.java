package com.shangho.blackcore.api.customerdemand.bean;

public class CustomerDemandToSpecialDemandBean implements java.io.Serializable {
	private static final long serialVersionUID = -2698716209359173487L;
	private int customerdemandid;
	private int specialdemanditemid;

	public int getCustomerdemandid() {
		return customerdemandid;
	}

	public int getSpecialdemanditemid() {
		return specialdemanditemid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDemandToSpecialDemandBean [customerdemandid=");
		builder.append(customerdemandid);
		builder.append(", specialdemanditemid=");
		builder.append(specialdemanditemid);
		builder.append("]");
		return builder.toString();
	}

	public CustomerDemandToSpecialDemandBean(int customerdemandid, int specialdemanditemid) {
		super();
		this.customerdemandid = customerdemandid;
		this.specialdemanditemid = specialdemanditemid;
	}

}
