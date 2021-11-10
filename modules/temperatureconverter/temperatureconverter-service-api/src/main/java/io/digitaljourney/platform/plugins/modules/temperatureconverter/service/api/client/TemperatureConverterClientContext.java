package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.client;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import io.digitaljourney.platform.modules.ws.rs.api.context.AbstractRSProxyContext;

/**
 * TemperatureConverter Client Context implementation.
 *
 * @since 1.0.0
 *
 */
//@formatter:off
@Component(
	service = TemperatureConverterClientContext.class,
	immediate = true
)
//@formatter:on
public class TemperatureConverterClientContext extends AbstractRSProxyContext {
	
	/**
	 * Method called when the component is activated.
	 *
	 * @param ctx Component context
	 */
	@Activate
	public void activate(ComponentContext ctx) {
		prepare(ctx);
	}
}
