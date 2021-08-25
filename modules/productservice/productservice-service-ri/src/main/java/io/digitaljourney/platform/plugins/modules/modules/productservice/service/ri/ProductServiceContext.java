package io.digitaljourney.platform.plugins.modules.modules.productservice.service.ri;

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
import io.digitaljourney.platform.plugins.modules.modules.productservice.service.api.exception.ProductServiceException;

// @formatter:off
@Component(
	service = { Object.class, ProductServiceContext.class },
	reference = {
		@Reference(
			name = ProductServiceResourceProperties.REF_PLATFORM_SECURITY_MANAGER,
			service = PlatformSecurityManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
        @Reference(
            name = ProductServiceResourceProperties.REF_PLATFORM_INVOCATION_MANAGER,
            service = PlatformInvocationManager.class,
            cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = ProductServiceResourceProperties.REF_ASYNC_MANAGER,
			service = PlatformAsyncManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = ProductServiceResourceProperties.REF_CACHE_MANAGER,
			service = PlatformCacheManager.class,
			cardinality = ReferenceCardinality.MANDATORY)
	})
@DocumentationResource(ProductServiceResourceProperties.DOCS_ADDRESS)
// @formatter:on
public final class ProductServiceContext extends AbstractRSEndpointContext {
	@Activate
	public void activate(ComponentContext ctx) {
		prepare(ctx);
	}

	public ProductServiceException exception(String message) {
		return ProductServiceException.of(this, message);
	}

	public ProductServiceException exception(Throwable cause) {
		return ProductServiceException.of(this, cause);
	}
}
