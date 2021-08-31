package io.digitaljourney.platform.plugins.modules.productmanagement.service.ri;

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
import io.digitaljourney.platform.plugins.modules.productmanagement.service.api.exception.ProductManagementException;

// @formatter:off
@Component(
	service = { Object.class, ProductManagementContext.class },
	reference = {
		@Reference(
			name = ProductManagementResourceProperties.REF_PLATFORM_SECURITY_MANAGER,
			service = PlatformSecurityManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
        @Reference(
            name = ProductManagementResourceProperties.REF_PLATFORM_INVOCATION_MANAGER,
            service = PlatformInvocationManager.class,
            cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = ProductManagementResourceProperties.REF_ASYNC_MANAGER,
			service = PlatformAsyncManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = ProductManagementResourceProperties.REF_CACHE_MANAGER,
			service = PlatformCacheManager.class,
			cardinality = ReferenceCardinality.MANDATORY)
	})
@DocumentationResource(ProductManagementResourceProperties.DOCS_ADDRESS)
// @formatter:on
public final class ProductManagementContext extends AbstractRSEndpointContext {
	@Activate
	public void activate(ComponentContext ctx) {
		prepare(ctx);
	}

	public ProductManagementException exception(String message) {
		return ProductManagementException.of(this, message);
	}

	public ProductManagementException exception(Throwable cause) {
		return ProductManagementException.of(this, cause);
	}
}
