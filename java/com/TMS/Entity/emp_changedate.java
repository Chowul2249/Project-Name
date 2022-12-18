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
@Table(name="emp_changedate")
public class emp_changedate {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="empchngdate_id")
	public int empchangedate;
	
	@Column(name="projectassign_id")
	public int projectassign;
	
	@Column(name="empchngdate_expiredt")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date empchangedate_expired;

	public int getEmpchngdate_id() {
		return empchangedate;
	}

	public void setEmpchngdate_id(int empchngdate_id) {
		this.empchangedate = empchngdate_id;
	}

	public int getProjectassign() {
		return projectassign;
	}

	public void setProjectassign(int projectassign) {
		this.projectassign = projectassign;
	}

	public Date getEmpchaagedate() {
		return empchangedate_expired;
	}

	public void setEmpchaagedate(Date empchaagedate) {
		this.empchangedate_expired = empchaagedate;
	}
	
	
}
