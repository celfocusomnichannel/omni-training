package io.digitaljourney.platform.plugins.apps.genericappkar.agents.core.impl;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
//import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.plugins.apps.genericappkar.agent.GenericAppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.genericappkar.common.api.agent.core.AbstractGenericAppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.FlagResponseDTO;
import io.digitaljourney.platform.plugins.apps.genericappkar.model.MusicProductResponseDTO;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.FlagMicroServiceRSXMLResource;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.ProductServiceResource;

//@formatter:off
@Component(
	service = { GenericAppKarCoreAgent.class },
	configurationPid = GenericAppKarCoreAgentConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE
)
@Designate(ocd = GenericAppKarCoreAgentConfig.class)
//@formatter:on
public class GenericAppKarCoreAgentImpl extends AbstractGenericAppKarCoreAgent<GenericAppKarCoreAgentConfig> {

	//FIXME Insert here your correlated microservice i.e resource
	/** The resource. */
	@Reference
	private volatile FlagMicroServiceRSXMLResource flagsResource;
	
	@Reference
	private volatile ProductServiceResource productsResource;
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param config Component configuration
	 */
	@Activate
	public void activate(GenericAppKarCoreAgentConfig config) {
		prepare(config);
	}

	/**
	 * Method called whenever the component configuration changes.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(GenericAppKarCoreAgentConfig config) {
		prepare(config);
	}
	
//	@Override
//	protected <microservice_name>Resource getResource() {
//		return resource;
//	}

	@Override
	protected String getUsername() {
		return getConfig().systemUserName();
	}

	@Override
	protected String getPassword() {
		return getConfig().systemPassword();
	}


	@Override
	protected FlagMicroServiceRSXMLResource getFlagResource() {
		return flagsResource;
	}

	@Override
	protected ProductServiceResource getProductResource() {
		return productsResource;
	}

}
