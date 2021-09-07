package io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
@ApiModel("CategoryDTO")
public class CategoryDTO {

	@ApiModelProperty(hidden = true)
	public Integer categoryId;

	@NotEmpty
	@ApiModelProperty(position = 1, required = true, value = "the category name")
	public String categoryName;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer id) {
		this.categoryId = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
