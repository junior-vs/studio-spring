package com.vsj.northwind.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
@Table(name="region")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="region_id", unique=true, nullable=false)
	private Integer regionId;

	@Column(name="region_description", nullable=false, length=2147483647)
	private String regionDescription;

	//bi-directional many-to-one association to Territory
	@OneToMany(mappedBy="region")
	private List<Territory> territories;

	public Region() {
	}

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionDescription() {
		return this.regionDescription;
	}

	public void setRegionDescription(String regionDescription) {
		this.regionDescription = regionDescription;
	}

	public List<Territory> getTerritories() {
		return this.territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}

	public Territory addTerritory(Territory territory) {
		getTerritories().add(territory);
		territory.setRegion(this);

		return territory;
	}

	public Territory removeTerritory(Territory territory) {
		getTerritories().remove(territory);
		territory.setRegion(null);

		return territory;
	}

}