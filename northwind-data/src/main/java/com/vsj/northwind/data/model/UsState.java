package com.vsj.northwind.data.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the us_states database table.
 * 
 */
@Entity
@Table(name="us_states")
@NamedQuery(name="UsState.findAll", query="SELECT u FROM UsState u")
public class UsState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="state_id", unique=true, nullable=false)
	private Integer stateId;

	@Column(name="state_abbr", length=2)
	private String stateAbbr;

	@Column(name="state_name", length=100)
	private String stateName;

	@Column(name="state_region", length=50)
	private String stateRegion;

	public UsState() {
	}

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateAbbr() {
		return this.stateAbbr;
	}

	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateRegion() {
		return this.stateRegion;
	}

	public void setStateRegion(String stateRegion) {
		this.stateRegion = stateRegion;
	}

}