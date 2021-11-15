package io.digitaljourney.platform.plugins.apps.genericappkar.common.api.facade;

import java.util.List;

import io.digitaljourney.platform.plugins.apps.genericappkar.dto.BookProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.MusicProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.TemperatureAppDTO;

public interface GenericAppKarFacade {
	
	//Temperature Converter
	public TemperatureAppDTO getTemperatureCelsius(Double tempCelsius);
	public TemperatureAppDTO getTemperatureFahrenheit(Double tempFahrenheit);
	
	//ProductService
	public List<MusicProductAppDTO> getArtistMusics(String artistName, String limit);
	public List<BookProductAppDTO> getWriterBooks(String writer, String limit);
	

}
