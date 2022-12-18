package com.TMS.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name="jobgrade")

public class Grade{


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="JOBGRADE_ID")
	private int id;

	
	@Column(name="JOBGRADE_NAME")
	private String jobGradeName; 
	

	@Column(name="JOBGRADE_STATUS")
	private String status;
	
	@Column(name="IPADDRESS")
	private String ipAddress;
	
	@Column(name="USERID")
	private int userId;
	
	@Column(name="CREATIONTIME")
	private java.sql.Timestamp timestamp;

	 
	    @Column(name="WEIGHTAGE")
	    private int weightage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobGradeName() {
		return jobGradeName;
	}

	public void setJobGradeName(String jobGradeName) {
		this.jobGradeName = jobGradeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}
	

	

}
	
	