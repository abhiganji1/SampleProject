package com.uvaan.sampleapi.param;

import java.util.Date;

public class UserParam {

	private Long id;
	private String email;
	private String password;
	private int createdby;
	private Date createddate;
	private int updatedby;
	private Date updaateddate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedDate() {
		return createddate;
	}
	public void setCreatedDate(Date createddate) {
		this.createddate = createddate;
	}
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public Date getUpdaateddate() {
		return updaateddate;
	}
	public void setUpdaateddate(Date updaateddate) {
		this.updaateddate = updaateddate;
	}
		
	
	
	

	}
