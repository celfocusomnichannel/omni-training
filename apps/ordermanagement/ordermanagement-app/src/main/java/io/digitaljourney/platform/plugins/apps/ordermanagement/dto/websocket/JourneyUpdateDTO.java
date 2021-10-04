package io.digitaljourney.platform.plugins.apps.ordermanagement.dto.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class JourneyUpdateDTO extends WebSocketJourneyDTO {
	public String action = "UPDATE";
	public String updatedBy;
	public String updatedChannel;
}
