package com.TMS.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="otheractivity_assign")
public class otheractivity_assign {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="otheractassign_id")
	public int otheractassign_id;
	
	@Column(name="empb_code")
	public int empb_id;
	
	@Column(name="otheract_id")
	public int otheract_id;
	
	@Column(name="assign_empcode")
	public int assign_empcode;
	
	@Column(name="otheractassign_status")
	public String otheractassign_status;

	public int getOtheractassign_id() {
		return otheractassign_id;
	}

	public void setOtheractassign_id(int otheractassign_id) {
		this.otheractassign_id = otheractassign_id;
	}

	
	public int getEmpb_id() {
		return empb_id;
	}

	public void setEmpb_id(int empb_id) {
		this.empb_id = empb_id;
	}

	public int getOtheract_id() {
		return otheract_id;
	}

	public void setOtheract_id(int otheract_id) {
		this.otheract_id = otheract_id;
	}

	public int getAssign_empcode() {
		return assign_empcode;
	}

	public void setAssign_empcode(int assign_empcode) {
		this.assign_empcode = assign_empcode;
	}

	public String getOtheractassign_status() {
		return otheractassign_status;
	}

	public void setOtheractassign_status(String otheractassign_status) {
		this.otheractassign_status = otheractassign_status;
	}

	
}
