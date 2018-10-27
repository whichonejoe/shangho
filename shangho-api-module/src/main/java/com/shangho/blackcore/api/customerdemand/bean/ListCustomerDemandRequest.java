package com.shangho.blackcore.api.customerdemand.bean;

import java.util.List;

public class ListCustomerDemandRequest implements java.io.Serializable {
	private static final long serialVersionUID = -668629005422240024L;
	private String token;
	private String status;
	private int budgetmax;
	private int budgetminimum;
	private int sqmax;
	private int sqminimum;
	private int houseagemax;
	private int houseageminimum;
	private List<Integer> categories;
	private List<String> names;
	private List<Integer> housepatternitemids;
	private List<Integer> locationitemids;
	private List<Integer> specialdemanditemids;
	private List<Integer> designateids;

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public int getBudgetmax() {
		return budgetmax;
	}

	public int getBudgetminimum() {
		return budgetminimum;
	}

	public int getSqmax() {
		return sqmax;
	}

	public int getSqminimum() {
		return sqminimum;
	}

	public int getHouseagemax() {
		return houseagemax;
	}

	public int getHouseageminimum() {
		return houseageminimum;
	}

	public List<Integer> getCategories() {
		return categories;
	}

	public List<String> getNames() {
		return names;
	}

	public List<Integer> getHousepatternitemids() {
		return housepatternitemids;
	}

	public List<Integer> getLocationitemids() {
		return locationitemids;
	}

	public List<Integer> getSpecialdemanditemids() {
		return specialdemanditemids;
	}

	public List<Integer> getDesignateids() {
		return designateids;
	}

	public void setHousepatternitemids(List<Integer> housepatternitemids) {
		this.housepatternitemids = housepatternitemids;
	}

	public void setLocationitemids(List<Integer> locationitemids) {
		this.locationitemids = locationitemids;
	}

	public void setSpecialdemanditemids(List<Integer> specialdemanditemids) {
		this.specialdemanditemids = specialdemanditemids;
	}

	public void setDesignateids(List<Integer> designateids) {
		this.designateids = designateids;
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
		builder.append("ListCustomerDemandRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", budgetmax=");
		builder.append(budgetmax);
		builder.append(", budgetminimum=");
		builder.append(budgetminimum);
		builder.append(", sqmax=");
		builder.append(sqmax);
		builder.append(", sqminimum=");
		builder.append(sqminimum);
		builder.append(", houseagemax=");
		builder.append(houseagemax);
		builder.append(", houseageminimum=");
		builder.append(houseageminimum);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", names=");
		builder.append(names);
		builder.append(", housepatternitemids=");
		builder.append(housepatternitemids);
		builder.append(", locationitemids=");
		builder.append(locationitemids);
		builder.append(", specialdemanditemids=");
		builder.append(specialdemanditemids);
		builder.append(", designateids=");
		builder.append(designateids);
		builder.append("]");
		return builder.toString();
	}

	public ListCustomerDemandRequest(String token, String status, int budgetmax, int budgetminimum, int sqmax,
			int sqminimum, int houseagemax, int houseageminimum, List<Integer> categories, List<String> names,
			List<Integer> housepatternitemids, List<Integer> locationitemids, List<Integer> specialdemanditemids,
			List<Integer> designateids) {
		super();
		this.token = token;
		this.status = status;
		this.budgetmax = budgetmax;
		this.budgetminimum = budgetminimum;
		this.sqmax = sqmax;
		this.sqminimum = sqminimum;
		this.houseagemax = houseagemax;
		this.houseageminimum = houseageminimum;
		this.categories = categories;
		this.names = names;
		this.housepatternitemids = housepatternitemids;
		this.locationitemids = locationitemids;
		this.specialdemanditemids = specialdemanditemids;
		this.designateids = designateids;
	}

}
