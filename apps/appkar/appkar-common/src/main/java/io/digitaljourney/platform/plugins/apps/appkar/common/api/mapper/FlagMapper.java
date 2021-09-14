package io.digitaljourney.platform.plugins.apps.appkar.common.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.apps.appkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.appkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.dto.FlagDTO;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.dto.MusicProductDTO;

@Mapper
public interface FlagMapper {
	public static final FlagMapper INSTANCE = Mappers.getMapper(FlagMapper.class);
	
	FlagResponseDTO toFlagResponseDTO(FlagDTO flagMicroserviceResponse);
	
	List<MusicProductResponseDTO> toListMusicProductResponseDTO(List<MusicProductDTO> musicProductMicroserviceResponse); 
}
