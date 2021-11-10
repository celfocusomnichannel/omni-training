package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.client;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.commons.PStrings;
import io.digitaljourney.platform.modules.ws.rs.api.RSProperties;
import io.digitaljourney.platform.modules.ws.rs.api.client.AbstractWSRSClient;
import io.digitaljourney.platform.modules.ws.rs.api.dao.WSRSConfig;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.TemperatureConverterResource;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.dto.TemperatureDTO;

/**
 * <p>
 * HTTP Client implementation of the {@link TemperatureConverterResource TemperatureConverter} API.
 * </p>
 * <p>
 * This client service accepts the following <b>Environment</b> variables:
 * </p>
 * <ul>
 * <li><b>OMNICHANNEL_TEMPERATURECONVERTER_ENDPOINT</b>: Used to set the service
 * endpoint</li>
 * <li><b>OMNICHANNEL_TEMPERATURECONVERTER_USERNAME</b>: Used to set the service
 * username</li>
 * <li><b>OMNICHANNEL_TEMPERATURECONVERTER_PASSWORD</b>: Used to set the service
 * password</li>
 * </ul>
 *
 * @since 1.0.0
 *
 */
//@formatter:off
@Component(
	configurationPid = TemperatureConverterClientConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = RSProperties.REF_CONTEXT,
			service = TemperatureConverterClientContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	// We need to set a below 0 service ranking to make this client will be returned by default
	// if there is another implementation available, for example the actual Service Implementation.
	// The digitaljourney.http.client config is totally optional.
	property = {
		org.osgi.framework.Constants.SERVICE_RANKING+":Integer=10",
		"digitaljourney.http.client=TemperatureConverter"
	}
)
@Designate(ocd = TemperatureConverterClientConfiguration.class)
//@formatter:on
public class TemperatureConverterClient extends AbstractWSRSClient<TemperatureConverterClientContext, TemperatureConverterClientConfiguration>
		implements TemperatureConverterResource {

	/**
	 * Creates a new default instance of the Service Client.
	 */
	public TemperatureConverterClient() {
		// empty constructor
	}

	@Override
	public void loadConfigurations() {
		// create an empty configuration and go with it
		WSRSConfig config = WSRSConfig.of(getServiceConfigurations(TemperatureConverterClientConfiguration.CPID));
		config.setName("temperatureconverter-httpclient");
		if (PStrings.isNullOrEmpty(config.getAddress())) {
			config.setAddress(getAddress());
		}
		if (PStrings.isNullOrEmpty(config.getUserName())) {
			config.setUserName(getUsername());
		}
		if (PStrings.isNullOrEmpty(config.getPassword())) {
			config.setPassword(getPassword());
		}

		super.prepare(config);
	}

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 */
	@Activate
	public void activate(ComponentContext ctx) {
		info("TemperatureConverter HTTP Client implementation activated  ({})", ctx);
		super.prepare(ctx);
		prepare((TemperatureConverterClientConfiguration) null);
	}
	
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Intent configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, TemperatureConverterClientConfiguration config) {
		info("TemperatureConverter HTTP Client implementation activated ({}, {})", ctx, config);
		super.prepare(ctx);
		prepare(config);
	}

	/**
	 * Method called whenever the intent provider configuration changes.
	 *
	 * @param config Intent configuration
	 */
	@Modified
	public void modified(TemperatureConverterClientConfiguration config) {
		info("TemperatureConverter HTTP Client configuration changed");
		prepare(config);
	}

	/**
	 * Method called whenever the endpoint intent is deactivated.
	 */
	@Deactivate
	public void deactivated() {
		info("TemperatureConverter HTTP Client implementation deactivated.");
	}

	@Override
	protected String getAddress() {
		String address = System.getenv("OMNICHANNEL_TEMPERATURECONVERTER_ENDPOINT");
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
		String username = System.getenv("OMNICHANNEL_TEMPERATURECONVERTER_USERNAME");
		if (username == null && getConfig() != null) {
			username = getConfig().userName();
		}
		return username;
	}

	@Override
	protected String getPassword() {
		String password = System.getenv("OMNICHANNEL_TEMPERATURECONVERTER_PASSWORD");
		if (password == null && getConfig() != null) {
			password = getConfig().password();
		}
		return decryptPassword(password);
	}

	@Override
	public TemperatureDTO convertCelsius(Double temperature) {
		// TODO Auto-generated method stub
		return null;
	}

}
