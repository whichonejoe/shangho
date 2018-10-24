package com.shangho.blackcore.api.customerdemand.request;

import java.util.List;

public class UpdateCustomerDemandRequest implements java.io.Serializable {
	private static final long serialVersionUID = -7682418743474785455L;
	private int id;
	private String token;
	private int objectcategoryid;
	private String status;
	private String name;
	private int budgetmax;
	private int budgetminimum;
	private int sqmax;
	private int sqminimum;
	private int houseagemax;
	private int houseageminimum;
	private List<Integer> housepatternitemids;
	private List<Integer> specialdemanditemids;
	private List<Integer> locationitemids;
	private List<Integer> designatepathids;

	public int getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public int getObjectcategoryid() {
		return objectcategoryid;
	}

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
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

	public List<Integer> getSpecialdemanditemids() {
		return specialdemanditemids;
	}

	public List<Integer> getLocationitemids() {
		return locationitemids;
	}

	public List<Integer> getDesignatepathids() {
		return designatepathids;
	}

	public void setHousepatternitemids(List<Integer> housepatternitemids) {
		this.housepatternitemids = housepatternitemids;
	}

	public void setSpecialdemanditemids(List<Integer> specialdemanditemids) {
		this.specialdemanditemids = specialdemanditemids;
	}

	public void setLocationitemids(List<Integer> locationitemids) {
		this.locationitemids = locationitemids;
	}

	public void setDesignatepathids(List<Integer> designatepathids) {
		this.designatepathids = designatepathids;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateCustomerDemandRequest [id=");
		builder.append(id);
		builder.append(", token=");
		builder.append(token);
		builder.append(", objectcategoryid=");
		builder.append(objectcategoryid);
		builder.append(", status=");
		builder.append(status);
		builder.append(", name=");
		builder.append(name);
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
		builder.append(", specialdemanditemids=");
		builder.append(specialdemanditemids);
		builder.append(", locationitemids=");
		builder.append(locationitemids);
		builder.append(", designatepathids=");
		builder.append(designatepathids);
		builder.append("]");
		return builder.toString();
	}

	public UpdateCustomerDemandRequest(int id, String token, int objectcategoryid, String status, String name,
			int budgetmax, int budgetminimum, int sqmax, int sqminimum, int houseagemax, int houseageminimum,
			List<Integer> housepatternitemids, List<Integer> specialdemanditemids, List<Integer> locationitemids,
			List<Integer> designatepathids) {
		super();
		this.id = id;
		this.token = token;
		this.objectcategoryid = objectcategoryid;
		this.status = status;
		this.name = name;
		this.budgetmax = budgetmax;
		this.budgetminimum = budgetminimum;
		this.sqmax = sqmax;
		this.sqminimum = sqminimum;
		this.houseagemax = houseagemax;
		this.houseageminimum = houseageminimum;
		this.housepatternitemids = housepatternitemids;
		this.specialdemanditemids = specialdemanditemids;
		this.locationitemids = locationitemids;
		this.designatepathids = designatepathids;
	}

}
