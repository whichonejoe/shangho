package com.shangho.blackcore.api.customerdemand.response;

import java.util.List;

import com.shangho.blackcore.api.customerdemand.bean.HousePatternNameBean;
import com.shangho.blackcore.api.customerdemand.bean.LocationNameBean;
import com.shangho.blackcore.api.customerdemand.bean.SpecialDemandNameBean;
import com.shangho.blackcore.api.designatepath.response.ListDesignatePathResponse;

public class ListCustomerDemandResponse implements java.io.Serializable {
	private static final long serialVersionUID = 2384735542585972221L;
	private int id;
	private String status;
	private String name;
	private int cagetory;
	private int budgetmax;
	private int budgetminimum;
	private int sqmax;
	private int sqminimum;
	private int houseagemax;
	private int houseageminimum;
	private List<HousePatternNameBean> housepatternitems;
	private List<LocationNameBean> locationitems;
	private List<SpecialDemandNameBean> specialdemanditems;
	private List<ListDesignatePathResponse> designates;

	public int getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public int getCagetory() {
		return cagetory;
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

	public List<HousePatternNameBean> getHousepatternitems() {
		return housepatternitems;
	}

	public List<LocationNameBean> getLocationitems() {
		return locationitems;
	}

	public List<SpecialDemandNameBean> getSpecialdemanditems() {
		return specialdemanditems;
	}

	public List<ListDesignatePathResponse> getDesignates() {
		return designates;
	}

	public void setHousepatternitems(List<HousePatternNameBean> housepatternitems) {
		this.housepatternitems = housepatternitems;
	}

	public void setLocationitems(List<LocationNameBean> locationitems) {
		this.locationitems = locationitems;
	}

	public void setSpecialdemanditems(List<SpecialDemandNameBean> specialdemanditems) {
		this.specialdemanditems = specialdemanditems;
	}

	public void setDesignates(List<ListDesignatePathResponse> designates) {
		this.designates = designates;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListCustomerDemandResponse [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", name=");
		builder.append(name);
		builder.append(", cagetory=");
		builder.append(cagetory);
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
		builder.append(", housepatternitems=");
		builder.append(housepatternitems);
		builder.append(", locationitems=");
		builder.append(locationitems);
		builder.append(", specialdemanditems=");
		builder.append(specialdemanditems);
		builder.append(", designates=");
		builder.append(designates);
		builder.append("]");
		return builder.toString();
	}

	public ListCustomerDemandResponse(int id, String status, String name, int cagetory, int budgetmax,
			int budgetminimum, int sqmax, int sqminimum, int houseagemax, int houseageminimum) {
		super();
		this.id = id;
		this.status = status;
		this.name = name;
		this.cagetory = cagetory;
		this.budgetmax = budgetmax;
		this.budgetminimum = budgetminimum;
		this.sqmax = sqmax;
		this.sqminimum = sqminimum;
		this.houseagemax = houseagemax;
		this.houseageminimum = houseageminimum;
	}

}
