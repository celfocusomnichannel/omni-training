package io.digitaljourney.platform.plugins.modules.temperatureconverter.data.api;

public interface TemperatureConverterDAO {
	
	public String getFahrenheitTemp(Double tempCelsius);
	
	public String getCelsiusTemp(Double tempFahrenheit);

}
