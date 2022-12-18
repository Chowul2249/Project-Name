package com.TMS.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_activity")
public class project_activity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="projectact_id")
	int projectact_id;
	
	@Column(name="projectdet_id")
	int projectdetid;
	 
	@Column(name="activity_id")
	int activity_id;

	public int getProjectact_id() {
		return projectact_id;
	}

	public void setProjectact_id(int projectact_id) {
		this.projectact_id = projectact_id;
	}

	public int getProjectdet_id() {
		return projectdetid;
	}

	public void setProjectdet_id(int projectdet_id) {
		this.projectdetid = projectdet_id;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	
}
