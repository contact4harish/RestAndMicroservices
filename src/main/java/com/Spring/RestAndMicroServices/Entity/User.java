package com.Spring.RestAndMicroServices.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

public class User {

	//for more annotations goto validations jar file and check out the constraints
	
	private Integer Id;
	@Size(min=2, max=15, message="Name should be of length[2-15]")
	private String name;
	@Past
	@NotNull
	private Date birthDate;
	
	
	@Override
	public String toString() {
		return "User [Id=" + Id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}



	public Integer getId() {
		return Id;
	}



	public void setId(Integer id) {
		Id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Date getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	public User(Integer Id, String name, Date birthDate) {
		// TODO Auto-generated constructor stub
		super();
		this.setId(Id);
		this.setName(name);
		this.setBirthDate(birthDate);
	}
	
	

}
