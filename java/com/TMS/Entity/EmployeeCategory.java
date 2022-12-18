package com.TMS.Entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="emp_category")
public class EmployeeCategory {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="id")
	private int id;
	
	@Column(name="empcat_name")
	private String empCatName;
	
	@Column(name="status")
	private String status;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="timestamp")
	private Timestamp timeStamp;
	
	@Column(name="tds_Status")
	private String tds_Status;
	
	
	
	public String getTds_Status() {
		return tds_Status;
	}

	public void setTds_Status(String tds_Status) {
		this.tds_Status = tds_Status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpCatName() {
		return empCatName;
	}

	public void setEmpCatName(String empCatName) {
		this.empCatName = empCatName;
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

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
