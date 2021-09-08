package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.client;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import io.digitaljourney.platform.modules.ws.rs.api.context.AbstractRSProxyContext;

//@formatter:off
@Component(
	service = FlagMicroserviceClientContext.class,
	immediate = true
)
//@formatter:on
public class FlagMicroserviceClientContext extends AbstractRSProxyContext{

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