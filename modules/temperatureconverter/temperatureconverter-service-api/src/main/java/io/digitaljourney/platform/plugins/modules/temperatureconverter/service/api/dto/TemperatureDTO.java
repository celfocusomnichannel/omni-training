package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.dto;

import org.osgi.dto.DTO;

public class TemperatureDTO extends DTO {

	public String temperatureConverted;
	public String symbol;

	public TemperatureDTO(String temperatureConverted, String symbol) {
		this.temperatureConverted = temperatureConverted;
		this.symbol = symbol;
	}

}
