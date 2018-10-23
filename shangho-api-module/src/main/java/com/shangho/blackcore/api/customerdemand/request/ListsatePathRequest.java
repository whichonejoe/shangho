package com.shangho.blackcore.api.customerdemand.request;

import java.util.List;

public class ListsatePathRequest implements java.io.Serializable {
	private static final long serialVersionUID = 6217837268877416631L;
	private String token;
	private String status;
	private List<String> names;
	private List<Integer> cagetories;
	private int budgetmax;
	private int budgetminimum;
	private int sqmax;
	private int sqminimum;
	private int houseagemax;
	private int houseageminimum;
	private List<Integer> housepatternitemids;
	private List<Integer> locationitemids;
	private List<Integer> specialdemanditemids;
	private List<String> designates;

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public List<String> getNames() {
		return names;
	}

	public List<Integer> getCagetories() {
		return cagetories;
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

	public List<Integer> getHousepatternitemids() {
		return housepatternitemids;
	}

	public List<Integer> getLocationitemids() {
		return locationitemids;
	}

	public List<Integer> getSpecialdemanditemids() {
		return specialdemanditemids;
	}

	public List<String> getDesignates() {
		return designates;
	}

	public void setNames(List<String> names) {
		this.names = names;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListsatePathRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", names=");
		builder.append(names);
		builder.append(", cagetories=");
		builder.append(cagetories);
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
		builder.append(", housepatternitemids=");
		builder.append(housepatternitemids);
		builder.append(", locationitemids=");
		builder.append(locationitemids);
		builder.append(", specialdemanditemids=");
		builder.append(specialdemanditemids);
		builder.append(", designates=");
		builder.append(designates);
		builder.append("]");
		return builder.toString();
	}

	public ListsatePathRequest(String token, String status, List<String> names, List<Integer> cagetories, int budgetmax,
			int budgetminimum, int sqmax, int sqminimum, int houseagemax, int houseageminimum,
			List<Integer> housepatternitemids, List<Integer> locationitemids, List<Integer> specialdemanditemids,
			List<String> designates) {
		super();
		this.token = token;
		this.status = status;
		this.names = names;
		this.cagetories = cagetories;
		this.budgetmax = budgetmax;
		this.budgetminimum = budgetminimum;
		this.sqmax = sqmax;
		this.sqminimum = sqminimum;
		this.houseagemax = houseagemax;
		this.houseageminimum = houseageminimum;
		this.housepatternitemids = housepatternitemids;
		this.locationitemids = locationitemids;
		this.specialdemanditemids = specialdemanditemids;
		this.designates = designates;
	}

}
