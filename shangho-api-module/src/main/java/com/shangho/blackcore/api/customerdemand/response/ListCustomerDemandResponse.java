package com.shangho.blackcore.api.customerdemand.response;

import java.util.List;

public class ListCustomerDemandResponse implements java.io.Serializable {
	private String token;
	private String status;
	private String name;
	private int cagetory;
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

}
