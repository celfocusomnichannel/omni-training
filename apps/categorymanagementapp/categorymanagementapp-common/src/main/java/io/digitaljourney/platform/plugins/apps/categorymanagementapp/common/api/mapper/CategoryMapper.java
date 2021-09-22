package io.digitaljourney.platform.plugins.apps.categorymanagementapp.common.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.dto.CategoryDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryResponseDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryCreateDTO;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.model.CategoryRequestDTO;

@Mapper
public interface CategoryMapper {
	public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	CategoryResponseDTO toCategoryResponseDTO(CategoryDTO category);
	
	CategoryDTO toCategoryDTO(CategoryRequestDTO category);
	
	CategoryDTO createToCategoryDTO(CategoryCreateDTO category);
	
	List<CategoryResponseDTO> toListCategoryResponseDTO(List<CategoryDTO> categoryList);

}
