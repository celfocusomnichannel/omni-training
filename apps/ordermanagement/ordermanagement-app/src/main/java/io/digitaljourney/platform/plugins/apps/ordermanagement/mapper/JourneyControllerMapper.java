package io.digitaljourney.platform.plugins.apps.ordermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;

@Mapper
public interface JourneyControllerMapper {
	public static final JourneyControllerMapper INSTANCE = Mappers.getMapper(JourneyControllerMapper.class);

	CustomJourneyDTO toProcess(CustomJourneyInstance instance);
}
