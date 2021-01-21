package com.vsj.northwind.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer_demographics database table.
 * 
 */
@Entity
@Table(name="customer_demographics")
@NamedQuery(name="CustomerDemographic.findAll", query="SELECT c FROM CustomerDemographic c")
public class CustomerDemographic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="customer_type_id", unique=true, nullable=false, length=2147483647)
	private String customerTypeId;

	@Column(name="customer_desc", length=2147483647)
	private String customerDesc;

	//bi-directional many-to-many association to Customer
	@ManyToMany(mappedBy="customerDemographics")
	private List<Customer> customers;

	public CustomerDemographic() {
	}

	public String getCustomerTypeId() {
		return this.customerTypeId;
	}

	public void setCustomerTypeId(String customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getCustomerDesc() {
		return this.customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}