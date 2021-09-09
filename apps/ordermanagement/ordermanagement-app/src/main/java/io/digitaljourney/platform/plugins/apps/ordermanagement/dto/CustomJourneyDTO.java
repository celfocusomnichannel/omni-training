package io.digitaljourney.platform.plugins.apps.ordermanagement.dto;

import java.util.Date;

import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.dto.JourneyResponseDTO;

public class CustomJourneyDTO extends JourneyResponseDTO {
	public String customField;
	public Date createDate;
}
