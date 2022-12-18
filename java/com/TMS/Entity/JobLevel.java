package com.TMS.Entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="joblevel")
public class JobLevel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	@Column(name="level_id")
	private int id;
	
	@Column(name="level_name")
	private String levelName;
	
	@Column(name="weightage")
	private int weightage;
	
	@Column(name="status")
	private String status;
	
	@Column(name="ip_address")
	private String ipAddress;

	@Column(name="user_id")
	private int userId;
	
	@Column(name="timestamp")
	private Timestamp timeStamp;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
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
