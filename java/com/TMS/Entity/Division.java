package com.TMS.Entity;

import java.sql.Timestamp;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="division")
public class Division {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="division_id")
	public int id;
	
	
	@Column(name="depart_id")
	public int departmentToDivision;
	
	@Column(name="division_name")
	public String division_name;
	
	@Column(name="division_status")
	public String status;
	
	@Column(name="division_ip")
	public String division_ip;
	
	@Column(name="division_user")
	public int division_user;
	
	@Column(name="division_time")
	public Timestamp division_time;
	
	@Column(name="department_to_division")
	public int departmenttodiv;
	
	

	public int getDepartmenttodiv() {
		return departmenttodiv;
	}

	public void setDepartmenttodiv(int departmenttodiv) {
		this.departmenttodiv = departmenttodiv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getDepartmentToDivision() {
		return departmentToDivision;
	}

	public void setDepartmentToDivision(int departmentToDivision) {
		this.departmentToDivision = departmentToDivision;
	}

	public String getDivision_name() {
		return division_name;
	}

	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDivision_ip() {
		return division_ip;
	}

	public void setDivision_ip(String division_ip) {
		this.division_ip = division_ip;
	}

	public int getDivision_user() {
		return division_user;
	}

	public void setDivision_user(int division_user) {
		this.division_user = division_user;
	}

	public Timestamp getDivision_time() {
		return division_time;
	}

	public void setDivision_time(Timestamp division_time) {
		this.division_time = division_time;
	}

	

}
