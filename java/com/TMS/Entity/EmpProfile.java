package com.TMS.Entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employeeprofile")
public class EmpProfile {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="empb_id")
	public int empb_id;
		
	@Column(name="empb_fname")
	public String empb_fname;
	
	@Column(name="empb_lname")
	public String empb_lname;	
	
	@Column(name="dob")
	public Date dob;
	
	@Column(name="empb_gend")
	public String empb_gend;
	
	@Column(name="empb_mobile")
	public String empb_mobile;
	
	@Column(name="empb_landline")
	public String empb_landline;
	
	@Column(name="empb_email")
	public String empb_email;
	
	@Column(name="empcat_id")
	public int empCatToEmp;
	
	@Column(name="print_cat_id")
	public int printCatId;
	
	@Column(name="depart_id")
	public int deptToempBasic;
	
	@Column(name="division_id")
	public int divToempBasic;
		
	@Column(name="desig_id")
	public int desigToEmp;
	
	@Column(name="level_id")
	public int levelToemp;
	
	@Column(name="empper_dtjoin")
	public Date dateOfJoin;
	
	@Column(name="empb_report")
	public String empb_report;
	
	@Column(name="empb_photo")
	public Blob empb_photo;
	
	@Column(name="empb_status")
	public String status;
	
	@Column(name="inactive_reason")
	public String inactive_reason;

	public Integer getEmpb_id() {
		return empb_id;
	}

	public void setEmpb_id(Integer empb_id) {
		this.empb_id = empb_id;
	}

	public String getEmpb_fname() {
		return empb_fname;
	}

	public void setEmpb_fname(String empb_fname) {
		this.empb_fname = empb_fname;
	}

	public String getEmpb_lname() {
		return empb_lname;
	}

	public void setEmpb_lname(String empb_lname) {
		this.empb_lname = empb_lname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmpb_gend() {
		return empb_gend;
	}

	public void setEmpb_gend(String empb_gend) {
		this.empb_gend = empb_gend;
	}

	public String getEmpb_mobile() {
		return empb_mobile;
	}

	public void setEmpb_mobile(String empb_mobile) {
		this.empb_mobile = empb_mobile;
	}

	public String getEmpb_landline() {
		return empb_landline;
	}

	public void setEmpb_landline(String empb_landline) {
		this.empb_landline = empb_landline;
	}

	public String getEmpb_email() {
		return empb_email;
	}

	public void setEmpb_email(String empb_email) {
		this.empb_email = empb_email;
	}

	public int getEmpCatToEmp() {
		return empCatToEmp;
	}

	public void setEmpCatToEmp(int empCatToEmp) {
		this.empCatToEmp = empCatToEmp;
	}

	public int getPrintCatId() {
		return printCatId;
	}

	public void setPrintCatId(int printCatId) {
		this.printCatId = printCatId;
	}

	public int getDeptToempBasic() {
		return deptToempBasic;
	}

	public void setDeptToempBasic(int deptToempBasic) {
		this.deptToempBasic = deptToempBasic;
	}

	public int getDivToempBasic() {
		return divToempBasic;
	}

	public void setDivToempBasic(int divToempBasic) {
		this.divToempBasic = divToempBasic;
	}

	public int getDesigToEmp() {
		return desigToEmp;
	}

	public void setDesigToEmp(int desigToEmp) {
		this.desigToEmp = desigToEmp;
	}

	public int getLevelToemp() {
		return levelToemp;
	}

	public void setLevelToemp(int levelToemp) {
		this.levelToemp = levelToemp;
	}

	public Date getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public String getEmpb_report() {
		return empb_report;
	}

	public void setEmpb_report(String empb_report) {
		this.empb_report = empb_report;
	}

	

	

	public Blob getEmpb_photo() {
		return empb_photo;
	}

	public void setEmpb_photo(Blob empb_photo) {
		this.empb_photo = empb_photo;
	}

	public void setEmpb_id(int empb_id) {
		this.empb_id = empb_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInactive_reason() {
		return inactive_reason;
	}

	public void setInactive_reason(String inactive_reason) {
		this.inactive_reason = inactive_reason;
	}
}