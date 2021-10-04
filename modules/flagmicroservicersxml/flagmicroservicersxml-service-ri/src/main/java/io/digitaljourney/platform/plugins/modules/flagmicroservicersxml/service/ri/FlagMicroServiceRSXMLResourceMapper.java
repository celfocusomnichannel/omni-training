package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.ri;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlagMicroServiceRSXMLResourceMapper {
  
	public static final FlagMicroServiceRSXMLResourceMapper INSTANCE = Mappers.getMapper(FlagMicroServiceRSXMLResourceMapper.class);
  
}
