package com.TMS.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="project_detail")
public class projectdetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="projectdet_id")
	int id;
	
	
	@Column(name="project_id")
	int project_id; 
	
	@Column(name="module_id")
	int module_id;
	
	@Column(name="projectdet_status")
	String projectdet_status;
	
	@Column(name="projectdet_adddt")
	@DateTimeFormat(pattern="dd/MM/yy")
	Date projectdet_adddt;
	
	@Column(name="projectdet_closedt")
	@DateTimeFormat(pattern="dd/MM/yy")
	Date projectdet_closedt;

	public int getId() {
		return id;
	}

	public int getProject_id() {
		return project_id;
	}

	public int getModule_id() {
		return module_id;
	}

	public String getProjectdet_status() {
		return projectdet_status;
	}

	public Date getProjectdet_adddt() {
		return projectdet_adddt;
	}

	public Date getProjectdet_closedt() {
		return projectdet_closedt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public void setProjectdet_status(String projectdet_status) {
		this.projectdet_status = projectdet_status;
	}

	public void setProjectdet_adddt(Date projectdet_adddt) {
		this.projectdet_adddt = projectdet_adddt;
	}

	public void setProjectdet_closedt(Date projectdet_closedt) {
		this.projectdet_closedt = projectdet_closedt;
	}	
}
