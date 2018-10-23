package com.shangho.blackcore.api.customerdemand.bean;

public class CustomerDemandToHousePatternBean implements java.io.Serializable {
	private static final long serialVersionUID = 1439256859398927169L;
	private int customerdemandid;
	private int housepatternitemid;

	public int getCustomerdemandid() {
		return customerdemandid;
	}

	public int getHousepatternitemid() {
		return housepatternitemid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDemandToHousePatternBean [customerdemandid=");
		builder.append(customerdemandid);
		builder.append(", housepatternitemid=");
		builder.append(housepatternitemid);
		builder.append("]");
		return builder.toString();
	}

	public CustomerDemandToHousePatternBean(int customerdemandid, int housepatternitemid) {
		super();
		this.customerdemandid = customerdemandid;
		this.housepatternitemid = housepatternitemid;
	}

}
