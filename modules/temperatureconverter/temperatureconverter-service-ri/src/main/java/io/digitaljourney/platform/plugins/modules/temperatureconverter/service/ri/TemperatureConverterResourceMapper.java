package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.ri;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TemperatureConverterResourceMapper {

	public static final TemperatureConverterResourceMapper INSTANCE = Mappers.getMapper(TemperatureConverterResourceMapper.class);

}
