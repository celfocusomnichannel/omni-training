package io.digitaljourney.platform.plugins.modules.modules.productservice.service.ri;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductServiceResourceMapper {
	public static final ProductServiceResourceMapper INSTANCE = Mappers.getMapper(ProductServiceResourceMapper.class);
}
