package io.digitaljourney.platform.plugins.apps.appkar.common.api.agent.core;

import java.lang.annotation.Annotation;
import java.util.List;

import io.digitaljourney.platform.modules.commons.AbstractConfigurableComponent;
import io.digitaljourney.platform.plugins.apps.appkar.agent.AppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.appkar.common.api.mapper.FlagMapper;
import io.digitaljourney.platform.plugins.apps.appkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.appkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.FlagMicroServiceRSXMLResource;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.ProductServiceResource;

public abstract class AbstractAppKarCoreAgent<A extends Annotation> extends AbstractConfigurableComponent<A> implements AppKarCoreAgent {

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
		return FlagMapper.INSTANCE.toFlagResponseDTO(getFlagResource().getFlag(alphaCode));
	}
	
	public List<MusicProductResponseDTO> getArtistMusics(String artistName, String limit) {
		return FlagMapper.INSTANCE.toListMusicProductResponseDTO(getProductResource().getArtistMusics(artistName, limit));
	}
}
