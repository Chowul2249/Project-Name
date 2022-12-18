package com.TMS.Entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="designation")
public class Designation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="desig_id")
	public Integer id;
	
	@Column(name="desig_name")
	public String desig_name;
	
	@Column(name="status")
	public String status;
	
	@Column(name="ip_address")
	public String ip_address;
	
	@Column(name="user_id")
	public int user_id;
	
	@Column(name="timestamp")
	public Timestamp timestamp;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesig_name() {
		return desig_name;
	}

	public void setDesig_name(String desig_name) {
		this.desig_name = desig_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
