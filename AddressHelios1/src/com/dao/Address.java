package com.dao;

public class Address implements java.io.Serializable {

	private long addressId;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address() {
	}

	public Address(String street, String city, String state, String zipcode, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
	}

	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	/*    CREATE TABLE  `employees`.`ADDRESS` (
    `ADDRESS_ID` int(32) NOT NULL auto_increment,
    PRIMARY KEY  (`ADDRESS_ID`), ADDRESS_STREET varchar(250), ADDRESS_CITY varchar(50),
    ADDRESS_STATE varchar(50), ADDRESS_ZIPCODE varchar(10)
  ) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;*/

}
