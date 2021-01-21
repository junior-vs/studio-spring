package com.vsj.northwind.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="customer_id", unique=true, nullable=false, length=2147483647)
	private String customerId;

	@Column(length=60)
	private String address;

	@Column(length=15)
	private String city;

	@Column(name="company_name", nullable=false, length=40)
	private String companyName;

	@Column(name="contact_name", length=30)
	private String contactName;

	@Column(name="contact_title", length=30)
	private String contactTitle;

	@Column(length=15)
	private String country;

	@Column(length=24)
	private String fax;

	@Column(length=24)
	private String phone;

	@Column(name="postal_code", length=10)
	private String postalCode;

	@Column(length=15)
	private String region;

	//bi-directional many-to-many association to CustomerDemographic
	@ManyToMany
	@JoinTable(
		name="customer_customer_demo"
		, joinColumns={
			@JoinColumn(name="customer_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="customer_type_id", nullable=false)
			}
		)
	private List<CustomerDemographic> customerDemographics;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer")
	private List<Order> orders;

	public Customer() {
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTitle() {
		return this.contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<CustomerDemographic> getCustomerDemographics() {
		return this.customerDemographics;
	}

	public void setCustomerDemographics(List<CustomerDemographic> customerDemographics) {
		this.customerDemographics = customerDemographics;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

}