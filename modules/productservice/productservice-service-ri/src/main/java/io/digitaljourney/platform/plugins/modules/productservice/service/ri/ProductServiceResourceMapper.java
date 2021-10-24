package io.digitaljourney.platform.plugins.modules.productservice.service.ri;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.modules.productservice.entity.MusicProduct;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.dto.MusicProductDTO;

@Mapper
public interface ProductServiceResourceMapper {
	public static final ProductServiceResourceMapper INSTANCE = Mappers.getMapper(ProductServiceResourceMapper.class);

	List<MusicProductDTO> toMusicProductDTO(List<MusicProduct> musicProducts);
}
