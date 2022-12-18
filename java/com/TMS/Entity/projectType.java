package com.TMS.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_type")
public class projectType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="projecttype_id")
	int id;
	
	@Column(name="projecttype_detail")
	String projecttype_details;
	
	@Column(name="projecttype_status")
	String projecttype_status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjecttype_details() {
		return projecttype_details;
	}

	public void setProjecttype_details(String projecttype_details) {
		this.projecttype_details = projecttype_details;
	}

	public String getProjecttype_status() {
		return projecttype_status;
	}

	public void setProjecttype_status(String projecttype_status) {
		this.projecttype_status = projecttype_status;
	}
	
}
