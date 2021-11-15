package io.digitaljourney.platform.plugins.apps.genericappkar.common.api.agent.core;

import java.lang.annotation.Annotation;
import java.util.List;

import io.digitaljourney.platform.modules.commons.AbstractConfigurableComponent;
import io.digitaljourney.platform.plugins.apps.genericappkar.agent.GenericAppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.genericappkar.common.api.mapper.GenericMapper;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.BookProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.MusicProductAppDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.dto.TemperatureAppDTO;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.ProductServiceResource;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.TemperatureConverterResource;

public abstract class AbstractGenericAppKarCoreAgent<A extends Annotation> extends AbstractConfigurableComponent<A> implements GenericAppKarCoreAgent {

	/**
	 * Gets the associated username.
	 *
	 * @return User name
	 */
	protected abstract String getUsername();

	/**
	 * Gets the associated password.
	 *
	 * @return User password
	 */
	protected abstract String getPassword();
	
	protected abstract ProductServiceResource getProductResource();
	
	protected abstract TemperatureConverterResource getTemperatureResource();
	
	@Override
	public TemperatureAppDTO getTemperatureCelsius(Double tempFahrenheit) {
		return GenericMapper.INSTANCE.toTemperatureAppDTO(getTemperatureResource().convertCelsius(tempFahrenheit));
	}

	public TemperatureAppDTO getTemperatureFahrenheit(Double tempCelsius) {
		return GenericMapper.INSTANCE.toTemperatureAppDTO(getTemperatureResource().convertFahrenheit(tempCelsius));
	}


	public List<MusicProductAppDTO> getArtistMusics(String artistName, String limit) {
		return GenericMapper.INSTANCE.toMusicProductAppDTO(getProductResource().getArtistMusics(artistName, limit));
	}


	public List<BookProductAppDTO> getWriterBooks(String writer, String limit) {
		return GenericMapper.INSTANCE.toBookProductAppDTO(getProductResource().getWriterBooks(writer, limit));
	}	
	
}
