package com.shangho.blackcore.api.designatepath.request;

import java.util.List;

public class ListDesignatePathRequest implements java.io.Serializable {
	private static final long serialVersionUID = 2663276853316884370L;
	private String token;
	private String status;
	private List<String> cities;
	private List<String> townships;
	private List<String> villages;
	private List<String> streets;
	private List<String> names;

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public void setTownships(List<String> townships) {
		this.townships = townships;
	}

	public void setVillages(List<String> villages) {
		this.villages = villages;
	}

	public void setStreets(List<String> streets) {
		this.streets = streets;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public List<String> getCities() {
		return cities;
	}

	public List<String> getTownships() {
		return townships;
	}

	public List<String> getVillages() {
		return villages;
	}

	public List<String> getStreets() {
		return streets;
	}

	public List<String> getNames() {
		return names;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListDesignatePathRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", cities=");
		builder.append(cities);
		builder.append(", townships=");
		builder.append(townships);
		builder.append(", villages=");
		builder.append(villages);
		builder.append(", streets=");
		builder.append(streets);
		builder.append(", names=");
		builder.append(names);
		builder.append("]");
		return builder.toString();
	}

	public ListDesignatePathRequest(String token, String status, List<String> cities, List<String> townships,
			List<String> villages, List<String> streets, List<String> names) {
		super();
		this.token = token;
		this.status = status;
		this.cities = cities;
		this.townships = townships;
		this.villages = villages;
		this.streets = streets;
		this.names = names;
	}

}
