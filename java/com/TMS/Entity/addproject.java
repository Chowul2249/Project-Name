package com.TMS.Entity;

import java.util.*;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="project")
public class addproject {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PROJECT_ID")
	private int id;
	
	@Column(name="PROJECT_NAME")
	private String name;
	
	@Column(name="PROJECT_CLIENT")
	private String client;
	
	@Column(name="PROJECT_ENDPOINT")
	private String endpoint;
	
	@Column(name="PROJECT_STARTDATE")
	@DateTimeFormat(pattern="dd/MM/yy")
	private Date startdate;
	
	@Column(name="PROJECT_ENDDATE")
	@DateTimeFormat(pattern="dd/MM/yy")
	private Date enddate;
	
	@Column(name="PROJECT_EXTEND")
	private String extend;
	
	@Column(name="PROJECT_CLIENTCONTACT")
	private String contactnumber;
	
	@Column(name="PROJECT_CLIENTCITY")
	private String city;
	
	@Column(name="PROJECT_CLIENTSTATE")
	private String state;
	
	@Column(name="PROJECT_CLIENTCOUNTRY")
	private String country;
	
	@Column(name="PROJECT_CLIENTADDRESS")
	private String address;
	
	@Column(name="PROJECT_CRTDATE")
	private Timestamp currentdate;
	
	@Column(name="depart_id")
	private int depart_id;
	

	@Column(name="projectytpe_id")
	private int projecttype_id;
	
	@Column(name="emp_code")
	private int emp_id;
	
	@Column(name="PROJECT_STATUS")
	private String status;

	
	public int getDepart_id() {
		return depart_id;
	}

	public void setDepart_id(int depart_id) {
		this.depart_id = depart_id;
	}

	public int getProjecttype_id() {
		return projecttype_id;
	}

	public void setProjecttype_id(int projecttype_id) {
		this.projecttype_id = projecttype_id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCurrentdate() {
		return currentdate;
	}

	public void setCurrentdate(Timestamp currentdate) {
		this.currentdate = currentdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
