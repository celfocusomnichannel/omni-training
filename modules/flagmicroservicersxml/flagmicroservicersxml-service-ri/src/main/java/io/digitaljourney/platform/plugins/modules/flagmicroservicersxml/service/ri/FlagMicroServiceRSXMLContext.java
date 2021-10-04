package io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.ri;

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
import io.digitaljourney.platform.plugins.modules.flagmicroservicersxml.service.api.exception.FlagMicroServiceRSXMLException;

// @formatter:off
@Component(
	service = { Object.class, FlagMicroServiceRSXMLContext.class },
	reference = {
		@Reference(
			name = FlagMicroServiceRSXMLResourceProperties.REF_PLATFORM_SECURITY_MANAGER,
			service = PlatformSecurityManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
        @Reference(
            name = FlagMicroServiceRSXMLResourceProperties.REF_PLATFORM_INVOCATION_MANAGER,
            service = PlatformInvocationManager.class,
            cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = FlagMicroServiceRSXMLResourceProperties.REF_ASYNC_MANAGER,
			service = PlatformAsyncManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = FlagMicroServiceRSXMLResourceProperties.REF_CACHE_MANAGER,
			service = PlatformCacheManager.class,
			cardinality = ReferenceCardinality.MANDATORY)
	})
@DocumentationResource(FlagMicroServiceRSXMLResourceProperties.DOCS_ADDRESS)
// @formatter:on
public final class FlagMicroServiceRSXMLContext extends AbstractRSEndpointContext {
	@Activate
	public void activate(ComponentContext ctx) {
		prepare(ctx);
	}

	public FlagMicroServiceRSXMLException exception(String message) {
		return FlagMicroServiceRSXMLException.of(this, message);
	}

	public FlagMicroServiceRSXMLException exception(Throwable cause) {
		return FlagMicroServiceRSXMLException.of(this, cause);
	}
}
