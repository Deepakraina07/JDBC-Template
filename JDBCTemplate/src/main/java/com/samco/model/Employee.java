package com.samco.model;


import org.springframework.data.annotation.Id;

public class Employee {

	@Id
	private int id;
	private String name;
	private String email;
	private String contact_no;
	private String city;
	
	public Employee() {
		super();
	}

	public Employee(int id, String name, String email, String contact_no,String city) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact_no = contact_no;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
