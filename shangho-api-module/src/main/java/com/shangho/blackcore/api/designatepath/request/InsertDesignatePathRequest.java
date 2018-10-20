package com.shangho.blackcore.api.designatepath.request;

public class InsertDesignatePathRequest implements java.io.Serializable {
	private static final long serialVersionUID = 8502334375422130507L;
	private String token;
	private String status;
	private String country;
	private String province;
	private String city;
	private String township;
	private String village;
	private String street;
	private String name;

	public String getToken() {
		return token;
	}

	public String getStatus() {
		return status;
	}

	public String getCountry() {
		return country;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getTownship() {
		return township;
	}

	public String getVillage() {
		return village;
	}

	public String getStreet() {
		return street;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsertDesignatePathRequest [token=");
		builder.append(token);
		builder.append(", status=");
		builder.append(status);
		builder.append(", country=");
		builder.append(country);
		builder.append(", province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append(", township=");
		builder.append(township);
		builder.append(", village=");
		builder.append(village);
		builder.append(", street=");
		builder.append(street);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

	public InsertDesignatePathRequest(String token, String status, String country, String province, String city,
			String township, String village, String street, String name) {
		super();
		this.token = token;
		this.status = status;
		this.country = country;
		this.province = province;
		this.city = city;
		this.township = township;
		this.village = village;
		this.street = street;
		this.name = name;
	}

}
