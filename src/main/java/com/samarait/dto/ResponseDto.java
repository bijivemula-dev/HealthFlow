package com.samarait.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	
	private Integer UUID;
	
	private String name;
	
	private String email;
	
	private String address;
	
	private String phoneNumber;
	
	

	
	public Integer getUUID() {
		return UUID;
	}

	public void setUUID(Integer uUID) {
		UUID = uUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
	

	

}
