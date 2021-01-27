package com.vsj.northwind.vos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.vsj.northwind.data.model.Product;

public class ProductVO {

	private Integer productId;

	@NotNull
	private Boolean discontinued;

	@NotBlank
	private String productName;

	private String quantityPerUnit;

	private Integer reorderLevel;

	private float unitPrice;

	private Integer unitsInStock;

	private Integer unitsOnOrder;

	// private List<OrderDetail> orderDetails;

	// private Category category;

	// private Supplier supplier;

	public ProductVO(Integer productId, @NotNull Boolean discontinued, @NotBlank String productName,
			String quantityPerUnit, Integer reorderLevel, float unitPrice, Integer unitsInStock, Integer unitsOnOrder) {
		super();
		this.productId = productId;
		this.discontinued = discontinued;
		this.productName = productName;
		this.quantityPerUnit = quantityPerUnit;
		this.reorderLevel = reorderLevel;
		this.unitPrice = unitPrice;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
	}

	public ProductVO(Product product) {
		this(product.getProductId(), product.isDiscontinued(), product.getProductName(), product.getQuantityPerUnit(),
				product.getReorderLevel(), product.getUnitPrice(), product.getUnitsInStock(),
				product.getUnitsOnOrder());
	}

	public Integer getProductId() {
		return productId;
	}

	public Boolean getDiscontinued() {
		return discontinued;
	}

	public String getProductName() {
		return productName;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public Integer getUnitsInStock() {
		return unitsInStock;
	}

	public Integer getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public Product map() {
		return new Product(this.productId, this.discontinued, this.productName, this.quantityPerUnit, this.reorderLevel,
				this.unitPrice, this.unitsInStock, this.unitsOnOrder);

	}

}
