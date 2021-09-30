package io.digitaljourney.platform.plugins.apps.ordermanagement.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.digitaljourney.platform.plugins.apps.ordermanagement.dto.CustomJourneyDTO;
import io.digitaljourney.platform.plugins.apps.ordermanagement.instance.CustomJourneyInstance;

@Mapper
public interface OrderManagementMapper {
	public static final OrderManagementMapper INSTANCE = Mappers.getMapper(OrderManagementMapper.class);

	CustomJourneyDTO toProcess(CustomJourneyInstance instance);
}
