package com.shangho.blackcore.api.customerdemand.bean;

public class CustomerDemandToLocationBean implements java.io.Serializable {
	private static final long serialVersionUID = -4212221054192740889L;
	private int customerdemandid;
	private int locationitemid;

	public int getCustomerdemandid() {
		return customerdemandid;
	}

	public int getLocationitemid() {
		return locationitemid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDemandToLocationBean [customerdemandid=");
		builder.append(customerdemandid);
		builder.append(", locationitemid=");
		builder.append(locationitemid);
		builder.append("]");
		return builder.toString();
	}

	public CustomerDemandToLocationBean(int customerdemandid, int locationitemid) {
		super();
		this.customerdemandid = customerdemandid;
		this.locationitemid = locationitemid;
	}

}
