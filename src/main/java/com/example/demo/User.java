package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

enum Classify {
	PLATINUM
	,GOLD
	,SILVER;
	
	static Classify calculate(int x) {
		if (x>=50000) {
			return PLATINUM;
		} else if (x>=30000 && x<50000) {
			return GOLD;
		} else if (x>=15000 && x<30000) {
			return SILVER;
		} else {
			throw new AssertionError("ERROR CODE 1001 - CLASSIFY not match : " + x);
		}
    }
}

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
    @NotBlank(message = "password is mandatory")
	private String password;
    
    @NotBlank(message = "user name is mandatory")
	private String userName;
    
    @NotBlank(message = "phone is mandatory")
//    @Pattern(regexp="^(0|[1-9][0-9]*)$")
	private String phone;
    
    @NotNull(message = "salary is mandatory")
	private Long salary;
    
	private String firstName;
	private String lastName;
	private String address;
	private String refCode;
	private Classify classify;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Classify getClassify() {
		return classify;
	}
	public void setClassify(Classify classify) {
		this.classify = classify;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

}
