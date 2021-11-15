package io.digitaljourney.platform.plugins.modules.temperatureconverter.service.ri;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import io.digitaljourney.platform.modules.async.api.PlatformAsyncManager;
import io.digitaljourney.platform.modules.cache.api.PlatformCacheManager;
import io.digitaljourney.platform.modules.commons.annotation.DocumentationResource;
import io.digitaljourney.platform.modules.invocation.api.PlatformInvocationManager;
import io.digitaljourney.platform.modules.security.api.PlatformSecurityManager;
import io.digitaljourney.platform.modules.ws.rs.api.context.AbstractRSEndpointContext;
import io.digitaljourney.platform.plugins.modules.temperatureconverter.service.api.exception.TemperatureConverterException;

// @formatter:off
@Component(
	service = { Object.class, TemperatureConverterContext.class },
	reference = {
		@Reference(
			name = TemperatureConverterResourceProperties.REF_PLATFORM_SECURITY_MANAGER,
			service = PlatformSecurityManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = TemperatureConverterResourceProperties.REF_PLATFORM_INVOCATION_MANAGER,
			service = PlatformInvocationManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = TemperatureConverterResourceProperties.REF_ASYNC_MANAGER,
			service = PlatformAsyncManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = TemperatureConverterResourceProperties.REF_CACHE_MANAGER,
			service = PlatformCacheManager.class,
			cardinality = ReferenceCardinality.MANDATORY)
	})
@DocumentationResource(TemperatureConverterResourceProperties.DOCS_ADDRESS)
// @formatter:on
public final class TemperatureConverterContext extends AbstractRSEndpointContext {
	@Activate
	public void activate(ComponentContext ctx) {
		prepare(ctx);
	}

	public TemperatureConverterException exception(String message) {
		return TemperatureConverterException.of(this, message);
	}

	public TemperatureConverterException exception(Throwable cause) {
		return TemperatureConverterException.of(this, cause);
	}
}
