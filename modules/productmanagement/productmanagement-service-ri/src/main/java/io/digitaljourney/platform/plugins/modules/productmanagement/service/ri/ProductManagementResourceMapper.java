package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Category;
import io.digitaljourney.platform.plugins.modules.productmanagement.entity.Product;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.ProductDTO;
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.SaveCategoryDTO;

@Mapper
public interface ProductManagementResourceMapper {
	public static final ProductManagementResourceMapper INSTANCE = Mappers.getMapper(ProductManagementResourceMapper.class);

	CategoryDTO toCategory(Category category);
	
	@InheritInverseConfiguration
	Category toCategory(CategoryDTO category);

	List<CategoryDTO> toCategories(List<Category> categories);
	
	ProductDTO toProduct(Product product);
	
	@InheritInverseConfiguration
	Product toProduct(ProductDTO product);
	
	List<ProductDTO> toProducts(List<Product> products);
	
	SaveCategoryDTO toSaveCategory(Category category);

	
}
