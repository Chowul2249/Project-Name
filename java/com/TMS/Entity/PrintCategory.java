package com.TMS.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="printCategory")
public class PrintCategory {
	
	
	 @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="printCat_id")
	public int id;
	
	@Column(name="printCat_name")
	public String printCat_name;
	
	@Column(name="printCat_status")
	public String status;
	
	@Column(name="printCat_ip")
	public String ipAddress;
	
	@Column(name="printCat_user")
	public int user;
	
	@Column(name="	printCat_time")
	public Timestamp timeStamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrintCat_name() {
		return printCat_name;
	}

	public void setPrintCat_name(String printCat_name) {
		this.printCat_name = printCat_name;
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

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	

}
