package io.digitaljourney.platform.plugins.apps.appkar.agents.core.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
//import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.plugins.apps.appkar.agent.AppKarCoreAgent;
import io.digitaljourney.platform.plugins.apps.appkar.common.api.agent.core.AbstractAppKarCoreAgent;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.FlagMicroServiceRSXMLResource;
//import io.digitaljourney.platform.plugins.modules.<microservice_name>.service.api.<microservice_name>Resource;
import io.digitaljourney.platform.plugins.modules.productservice.service.api.ProductServiceResource;


//@formatter:off
@Component(
	service = { AppKarCoreAgent.class },
	configurationPid = AppKarCoreAgentConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE
)
@Designate(ocd = AppKarCoreAgentConfig.class)
//@formatter:on
public class AppKarCoreAgentImpl extends AbstractAppKarCoreAgent<AppKarCoreAgentConfig> {

	//FIXME Insert here your correlated microservice i.e resource
	/** The resource. */
	//@Reference
	//private volatile <microservice_name>Resource resource;
	
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
	public void activate(AppKarCoreAgentConfig config) {
		prepare(config);
	}

	/**
	 * Method called whenever the component configuration changes.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(AppKarCoreAgentConfig config) {
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

	
	public FlagMicroServiceRSXMLResource getFlagResource() {
		return flagsResource;
	}
	
	public ProductServiceResource getProductResource() {
		return productsResource;
	}

	
}
