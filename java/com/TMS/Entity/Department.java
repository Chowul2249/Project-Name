package com.TMS.Entity;

import java.sql.Timestamp;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name="department")
public class Department {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="depart_id")
	public int id;
	
	@Column(name="depart_name")
	public String depart_name;
	
	@Column(name="depart_status")
	public String status;
	
	@Column(name="depart_ip")
	public String depart_ip;
	
	@Column(name="depart_user")
	public int depart_user;
	
	@Column(name="depart_time")
	public Timestamp depart_time;

	/*@OneToMany(mappedBy="departmentToDivision")
	public List<Division> div;
	
	@OneToMany(mappedBy="deptToempBasic")
	public List<Employee_Basic> deptForEmp;*/

	/*public List<Employee_Basic> getDeptForEmp() {
		return deptForEmp;
	}

	public void setDeptForEmp(List<Employee_Basic> deptForEmp) {
		this.deptForEmp = deptForEmp;
	}
*/
	public int getId() {
		return id;
	}
/*
	public List<Division> getDiv() {
		return div;
	}

	public void setDiv(List<Division> div) {
		this.div = div;
	}*/

	public void setId(int id) {
		this.id = id;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepart_ip() {
		return depart_ip;
	}

	public void setDepart_ip(String depart_ip) {
		this.depart_ip = depart_ip;
	}

	public int getDepart_user() {
		return depart_user;
	}

	public void setDepart_user(int depart_user) {
		this.depart_user = depart_user;
	}

	public Timestamp getDepart_time() {
		return depart_time;
	}

	public void setDepart_time(Timestamp depart_time) {
		this.depart_time = depart_time;
	}

}
