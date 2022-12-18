package com.TMS.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="otheractivity_chng")
public class otheractivity_change {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="otheractchng_id")
	public int otheractchngid;
	
	@Column(name="otheractassign_id")
	public int otheractassignid;
	
	@Column(name="otheractchng_date")
	public Timestamp otheractchngdate;
	
	@Column(name="otheractchng_status")
	public String otheractchng_status;

	public int getOtheractchngid() {
		return otheractchngid;
	}

	public void setOtheractchngid(int otheractchngid) {
		this.otheractchngid = otheractchngid;
	}

	public int getOtheractassignid() {
		return otheractassignid;
	}

	public void setOtheractassignid(int otheractassignid) {
		this.otheractassignid = otheractassignid;
	}
   


	public Timestamp getOtheractchngdate() {
		return otheractchngdate;
	}

	public void setOtheractchngdate(Timestamp otheractchngdate) {
		this.otheractchngdate = otheractchngdate;
	}

	public String getOtheractchng_status() {
		return otheractchng_status;
	}

	public void setOtheractchng_status(String otheractchng_status) {
		this.otheractchng_status = otheractchng_status;
	}
}
