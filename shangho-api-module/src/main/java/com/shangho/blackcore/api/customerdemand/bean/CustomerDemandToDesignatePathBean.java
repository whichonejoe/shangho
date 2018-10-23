package com.shangho.blackcore.api.customerdemand.bean;

public class CustomerDemandToDesignatePathBean implements java.io.Serializable {
	private static final long serialVersionUID = -5522928689743820949L;
	private int customerdemandid;
	private int designatepathid;

	public int getCustomerdemandid() {
		return customerdemandid;
	}

	public int getDesignatepathid() {
		return designatepathid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDemandToDesignatePathBean [customerdemandid=");
		builder.append(customerdemandid);
		builder.append(", designatepathid=");
		builder.append(designatepathid);
		builder.append("]");
		return builder.toString();
	}

	public CustomerDemandToDesignatePathBean(int customerdemandid, int designatepathid) {
		super();
		this.customerdemandid = customerdemandid;
		this.designatepathid = designatepathid;
	}

}
