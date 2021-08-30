package io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.ri;

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
import io.digitaljourney.platform.plugins.modules.modules.trainingmicroservicersxml.service.api.exception.TrainingMicroserviceRSXMLException;

// @formatter:off
@Component(
	service = { Object.class, TrainingMicroserviceRSXMLContext.class },
	reference = {
		@Reference(
			name = TrainingMicroserviceRSXMLResourceProperties.REF_PLATFORM_SECURITY_MANAGER,
			service = PlatformSecurityManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
        @Reference(
            name = TrainingMicroserviceRSXMLResourceProperties.REF_PLATFORM_INVOCATION_MANAGER,
            service = PlatformInvocationManager.class,
            cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = TrainingMicroserviceRSXMLResourceProperties.REF_ASYNC_MANAGER,
			service = PlatformAsyncManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = TrainingMicroserviceRSXMLResourceProperties.REF_CACHE_MANAGER,
			service = PlatformCacheManager.class,
			cardinality = ReferenceCardinality.MANDATORY)
	})
@DocumentationResource(TrainingMicroserviceRSXMLResourceProperties.DOCS_ADDRESS)
// @formatter:on
public final class TrainingMicroserviceRSXMLContext extends AbstractRSEndpointContext {
	@Activate
	public void activate(ComponentContext ctx) {
		prepare(ctx);
	}

	public TrainingMicroserviceRSXMLException exception(String message) {
		return TrainingMicroserviceRSXMLException.of(this, message);
	}

	public TrainingMicroserviceRSXMLException exception(Throwable cause) {
		return TrainingMicroserviceRSXMLException.of(this, cause);
	}
}
