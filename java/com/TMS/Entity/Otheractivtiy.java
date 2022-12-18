package com.TMS.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="other_activity")
public class Otheractivtiy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="otheract_id")
	private int id;
	
	@Column(name="otheract_det")
	private String otheract_det;
	
	@Column(name="otheract_status")
	private String otheract_status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOtheract_det() {
		return otheract_det;
	}

	public void setOtheract_det(String otheract_det) {
		this.otheract_det = otheract_det;
	}

	public String getOtheract_status() {
		return otheract_status;
	}

	public void setOtheract_status(String otheract_status) {
		this.otheract_status = otheract_status;
	}	
}
