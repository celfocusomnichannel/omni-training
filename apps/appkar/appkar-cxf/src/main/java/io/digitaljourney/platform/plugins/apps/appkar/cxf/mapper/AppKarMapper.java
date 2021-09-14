package io.digitaljourney.platform.plugins.apps.appkar.cxf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppKarMapper {

	public static final AppKarMapper INSTANCE = Mappers.getMapper(AppKarMapper.class);

}
