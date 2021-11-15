package io.digitaljourney.platform.plugins.apps.genericappkar.cxf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenericAppKarMapper {

	public static final GenericAppKarMapper INSTANCE = Mappers.getMapper(GenericAppKarMapper.class);

}
