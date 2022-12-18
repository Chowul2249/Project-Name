package com.TMS.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activity")
public class activity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACTIVITY_ID")
	private int id;
	
	@Column(name="ACTIVITY_DETAILS")
	private String activity_dts;
	
	@Column(name="ACTIVITY_STATUS")
	private String activity_status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivity_dts() {
		return activity_dts;
	}

	public void setActivity_dts(String activity_dts) {
		this.activity_dts = activity_dts;
	}

	public String getActivity_status() {
		return activity_status;
	}

	public void setActivity_status(String activity_status) {
		this.activity_status = activity_status;
	}

	
	
}
