package com.vsj.northwind.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the territories database table.
 * 
 */
@Entity
@Table(name="territories")
@NamedQuery(name="Territory.findAll", query="SELECT t FROM Territory t")
public class Territory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="territory_id", unique=true, nullable=false, length=20)
	private String territoryId;

	@Column(name="territory_description", nullable=false, length=2147483647)
	private String territoryDescription;

	//bi-directional many-to-many association to Employee
	@ManyToMany
	@JoinTable(
		name="employee_territories"
		, joinColumns={
			@JoinColumn(name="territory_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="employee_id", nullable=false)
			}
		)
	private List<Employee> employees;

	//bi-directional many-to-one association to Region
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id", nullable=false)
	private Region region;

	public Territory() {
	}

	public String getTerritoryId() {
		return this.territoryId;
	}

	public void setTerritoryId(String territoryId) {
		this.territoryId = territoryId;
	}

	public String getTerritoryDescription() {
		return this.territoryDescription;
	}

	public void setTerritoryDescription(String territoryDescription) {
		this.territoryDescription = territoryDescription;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}