package io.digitaljourney.platform.plugins.apps.genericappkar.common.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.apps.genericappkar.dto.BookProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.MusicProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.TemperatureAppDTO;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.dto.BookProductDTO;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.dto.MusicProductDTO;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.dto.TemperatureDTO;



@Mapper
public interface GenericMapper {
	
	public static final GenericMapper INSTANCE = Mappers.getMapper(GenericMapper.class);
	
	TemperatureAppDTO toTemperatureAppDTO (TemperatureDTO tempDTO);
	
	List<BookProductAppDTO> toBookProductAppDTO (List<BookProductDTO> bookDTO);
	
	List<MusicProductAppDTO> toMusicProductAppDTO (List<MusicProductDTO> musicDTO);

}
