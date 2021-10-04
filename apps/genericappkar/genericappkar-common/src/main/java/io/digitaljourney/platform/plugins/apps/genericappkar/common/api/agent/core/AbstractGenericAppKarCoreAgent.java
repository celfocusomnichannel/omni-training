package io.digitaljourney.platform.plugins.apps.genericappkar.common.api.agent.core;

import java.lang.annotation.Annotation;
import java.util.List;

import io.digitaljourney.platform.modules.commons.AbstractConfigurableComponent;
import io.digitaljourney.platform.plugins.apps.genericappkar.agent.GenericAppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.genericappkar.common.mapper.ServicesMapper;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.FlagMicroServiceRSXMLResource;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.ProductServiceResource;

public abstract class AbstractGenericAppKarCoreAgent<A extends Annotation> extends AbstractConfigurableComponent<A> implements GenericAppKarCoreAgent {

	//FIXME Insert here your correlated microservice i.e resource
	/**
	 * Gets the corresponding <microservice_name>Resource implementation.
	 *
	 * @return <microservice_name>Resource
	 */
	//protected abstract <microservice_name>Resource getResource();	

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

protected abstract FlagMicroServiceRSXMLResource getFlagResource();
	
	protected abstract ProductServiceResource getProductResource();
	
	public FlagResponseDTO getFlag(String alphaCode) {	
		return ServicesMapper.INSTANCE.toFlagResponseDTO(getFlagResource().getFlag(alphaCode));
	}
	
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit) {
		return ServicesMapper.INSTANCE.toListMusicProductResponseDTO(getProductResource().getArtistMusics(artistName, limit));
	}
}
