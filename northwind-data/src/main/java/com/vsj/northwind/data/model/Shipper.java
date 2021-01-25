package com.vsj.northwind.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shippers database table.
 * 
 */
@Entity
@Table(name="shippers")
@NamedQuery(name="Shipper.findAll", query="SELECT s FROM Shipper s")
public class Shipper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="shipper_id", unique=true, nullable=false)
	private Integer shipperId;

	@Column(name="company_name", nullable=false, length=40)
	private String companyName;

	@Column(length=24)
	private String phone;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="shipper")
	private List<Order> orders;

	public Shipper() {
	}

	public Integer getShipperId() {
		return this.shipperId;
	}

	public void setShipperId(Integer shipperId) {
		this.shipperId = shipperId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setShipper(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setShipper(null);

		return order;
	}

}