package com.TMS.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="module")
public class module {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MODULE_ID")
	private int id;
	
	@Column(name="MODEULE_DETAIL")
	private String module_detail;
	
	@Column(name="MODULE_STATUS")
	private String module_status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModule_detail() {
		return module_detail;
	}

	public void setModule_detail(String module_detail) {
		this.module_detail = module_detail;
	}

	public String getModule_status() {
		return module_status;
	}

	public void setModule_status(String module_status) {
		this.module_status = module_status;
	}

	

}
