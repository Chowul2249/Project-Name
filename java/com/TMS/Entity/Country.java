package com.TMS.Entity;

import java.sql.Timestamp;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity

@Table(name="country")
public class Country {
	
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="country_id")
	 private int id;
		
	@Column(name="countryname",unique=true)
	private String countryName;
	
	@Column(name="status")
	private String status;
	
	@Column(name="user_id")
	private int user;
	
	@Column(name="ipaddress")
	private String ipAddress;
	
	@Column(name="creationtime")
	private Timestamp creationTime;
	
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	
	

}
