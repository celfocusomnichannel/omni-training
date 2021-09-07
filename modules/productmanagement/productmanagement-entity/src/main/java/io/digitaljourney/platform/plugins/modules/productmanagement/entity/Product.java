package io.digitaljourney.platform.plugins.modules.productmanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@Entity
@Table(name = "PM_PRODUCT", uniqueConstraints = @UniqueConstraint(columnNames = "PRODUCTID", name = "PM_PRODUCT_PK"))
@GeneratePojoBuilder
public class Product {
	@Id
	@SequenceGenerator(name = "productSeqTask", sequenceName = "PM_PRODUCT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeqTask")

	@Column(name = "PRODUCTID", updatable = false, nullable = false)
	private Integer productId;

	@Column(name = "PRODUCTNAME", nullable = false)
	private String productName;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="CATEGORYID", nullable = false)
	private Category category;

	@Column(name = "PRODUCTPRICE", nullable = false)
	private Double productPrice;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer id) {
		this.productId = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
