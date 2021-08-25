package io.digitaljourney.platform.plugins.modules.modules.productservice.data.ri;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import io.digitaljourney.platform.modules.invocation.api.PlatformInvocationManager;
import io.digitaljourney.platform.modules.uriql.api.UriqlProvider;
import io.digitaljourney.platform.modules.ws.api.WSProperties;
import io.digitaljourney.platform.modules.ws.rs.api.context.AbstractRSProxyContext;

// @formatter:off
@Component(
	service = WSContext.class,
	reference = {
		@Reference(
			name = WSProperties.REF_URIQL,
			service = UriqlProvider.class,
			cardinality = ReferenceCardinality.MANDATORY),
        @Reference(
            name = WSProperties.REF_PLATFORM_INVOCATION_MANAGER,
            service = PlatformInvocationManager.class,
            cardinality = ReferenceCardinality.MANDATORY)
	})
// @formatter:on
public final class WSContext extends AbstractRSProxyContext {
	@Activate
	public void activate(ComponentContext ctx) {
		prepare(ctx);
	}
}
