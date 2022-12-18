package com.TMS.Entity;

import java.sql.Timestamp;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="city_id")
	private int id;
	 	
	
	@Column(name="country_id")
	private int mappingCountryCity;
	
	
	@Column(name="state_id")
	private int mappingStateCity;
	
	@Column(name="user_id")
	private int user;
	
	@Column(name="ipaddress",length=25)
	private String ipAddress;
	
	@Column(name="creationtime")
	private Timestamp creationTime;
	
	@Column(name="city_name",length=50)
	private String cityName;
	
	@Column(name="status",length=5)
	private String status;
	
	@Column(name="location")
	private String location;
	
	@Column(name="POPULATION")
	private String population;
	
	@Column(name="REMOTE_LOCATION")
	private String remoteLocation;
	
	
	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getRemoteLocation() {
		return remoteLocation;
	}

	public void setRemoteLocation(String remoteLocation) {
		this.remoteLocation = remoteLocation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMappingCountryCity() {
		return mappingCountryCity;
	}

	public void setMappingCountryCity(int mappingCountryCity) {
		this.mappingCountryCity = mappingCountryCity;
	}

	public int getMappingStateCity() {
		return mappingStateCity;
	}

	public void setMappingStateCity(int mappingStateCity) {
		this.mappingStateCity = mappingStateCity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	

}
