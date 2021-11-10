package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.dto;

public class TemperatureDTO {

	public String temperatureConverted;
	public String symbol;

	public TemperatureDTO(String temperatureConverted, String symbol) {
		this.temperatureConverted = temperatureConverted;
		this.symbol = symbol;
	}

}
