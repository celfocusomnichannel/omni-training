package io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
@ApiModel("Product")
public class ProductDTO {

	@ApiModelProperty(hidden = true)
	public Integer productId;

	@NotEmpty
	@ApiModelProperty(position = 1, required = true, value = "the product name")
	public String productName;

	@NotNull
	@ApiModelProperty(position = 2, required = true, value = "the product price")
	public Double productPrice;

	@NotNull
	@ApiModelProperty(position = 3, required = true, value = "the products category")
	public SaveCategoryDTO category;

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

	public SaveCategoryDTO getCategory() {
		return category;
	}

	public void setCategory(SaveCategoryDTO category) {
		this.category = category;
	}

}
