package io.digitaljourney.platform.plugins.apps.genericappkar.agent;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import io.digitaljourney.platform.plugins.apps.genericappkar.dto.BookProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.MusicProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.TemperatureAppDTO;

@ProviderType
public interface GenericAppKarCoreAgent {

	//Temperature Converter
	public TemperatureAppDTO getTemperatureCelsius(Double tempCelsius);
	public TemperatureAppDTO getTemperatureFahrenheit(Double tempFahrenheit);
	
	//ProductService
	public List<MusicProductAppDTO> getArtistMusics(String artistName, String limit);
	public List<BookProductAppDTO> getWriterBooks(String writer, String limit);
	
}
