package io.digitaljourney.platform.plugins.apps.genericappkar.common.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.apps.genericappkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.dto.FlagDTO;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.dto.MusicProductDTO;

@Mapper
public interface ServicesMapper {
	public static final ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

	FlagResponseDTO toFlagResponseDTO(FlagDTO flagMicroserviceResponse);
	
	List<MusicProductResponseDTO> toListMusicProductResponseDTO(List<MusicProductDTO> musicProductMicroserviceResponse); 	
	
}
