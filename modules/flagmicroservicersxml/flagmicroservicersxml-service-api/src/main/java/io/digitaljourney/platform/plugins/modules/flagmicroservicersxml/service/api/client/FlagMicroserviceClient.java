package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.api.dao.WSData;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.modules.ws.rs.api.client.AbstractWSRSClient;
import io.digitaljourney.platform.modules.ws.rs.api.dao.WSRSConfig;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.FlagMicroServiceRSXMLResource;
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.dto.FlagDTO;

//@formatter:off
@Component(
	configurationPid = FlagMicroserviceClientConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = RSProperties.REF_CONTEXT,
			service = FlagMicroserviceClientContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	property = {
		org.osgi.framework.Constants.SERVICE_RANKING+":Integer=10",
		"digitaljourney.http.client=Product Service"
	}
)
@Designate(ocd = FlagMicroserviceClientConfiguration.class)
//@formatter:on
public class FlagMicroserviceClient extends AbstractWSRSClient<FlagMicroserviceClientContext, FlagMicroserviceClientConfiguration> implements FlagMicroServiceRSXMLResource{
	
	/**
	 * Creates a new default instance of the Service Client.
	 */
	public FlagMicroserviceClient() {
		// empty constructor
	}
	
	@Override
	public void loadConfigurations() {
		// create an empty configuration and go with it
		WSRSConfig config = WSRSConfig.of(getServiceConfigurations(FlagMicroserviceClientConfiguration.CPID));
		config.setName("flagmicroservicersxml-httpclient");
		if (config.getAddress() == null) {
			config.setAddress(getAddress());
		}
		if (config.getUserName() == null) {
			config.setUserName(getUsername());
		}
		if (config.getPassword() == null) {
			config.setPassword(getPassword());
		}

		super.prepare(config);
	}
	
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx Component context
	 */
	@Activate
	public void activate(ComponentContext ctx) {
		info("ProductManagement HTTP Client implementation activated  ({})", ctx);
		super.prepare(ctx);
		prepare((FlagMicroserviceClientConfiguration) null);
	}
	
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Intent configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, FlagMicroserviceClientConfiguration config) {
		info("ProductManagement HTTP Client implementation activated ({}, {})", ctx, config);
		super.prepare(ctx);
		prepare(config);
	}
	
	/**
	 * Method called whenever the intent provider configuration changes.
	 *
	 * @param config Intent configuration
	 */
	@Modified
	public void modified(FlagMicroserviceClientConfiguration config) {
		info("ProductService HTTP Client configuration changed");
		prepare(config);
	}
	
	/**
	 * Method called whenever the endpoint intent is deactivated.
	 */
	@Deactivate
	public void deactivated() {
		info("ProductManagement HTTP Client implementation deactivated.");
	}
	
	@Override
	protected String getAddress() {
		String address = System.getenv("OMNICHANNEL_PRODUCTMANAGEMENT_ENDPOINT");
		if (address == null && getConfig() != null) {
			address = getConfig().address();
		}
		if (address == null) {
			address = super.getAddress();
		}
		return address;
	}
	
	@Override
	protected String getUsername() {
		String username = System.getenv("OMNICHANNEL_PRODUCTMANAGEMENT_USERNAME");
		if (username == null && getConfig() != null) {
			username = getConfig().userName();
		}
		return username;
	}

	@Override
	protected String getPassword() {
		String password = System.getenv("OMNICHANNEL_PRODUCTMANAGEMENT_USERNAME");
		if (password == null && getConfig() != null) {
			password = getConfig().password();
		}
		return decryptPassword(password);
	}

	@Override
	public FlagDTO getFlag(String alphaCode) {
		WSData<FlagDTO> result = super.invoke((WebClient client) -> {
			WebClient clientPath = client.type(MediaType.MULTIPART_FORM_DATA).path("/{alphaCode}", alphaCode);
			// allow making changes to the client
			executeClientHandlers(clientPath);
			FlagDTO responseCall = clientPath.get(FlagDTO.class);
			return WSData.of(responseCall).build();
		});
		if (!result.success() || result.data() == null) {
			throw resultException(result);
		}
		return result.data();
	}
}




