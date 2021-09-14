package io.digitaljourney.platform.plugins.apps.appkar.cxf;

import io.digitaljourney.platform.modules.async.api.PlatformAsyncManager;
import io.digitaljourney.platform.modules.cache.api.PlatformCacheManager;
import io.digitaljourney.platform.modules.commons.annotation.DocumentationResource;
import io.digitaljourney.platform.modules.invocation.api.PlatformInvocationManager;
import io.digitaljourney.platform.modules.security.api.PlatformSecurityManager;
import io.digitaljourney.platform.modules.ws.rs.api.context.AbstractRSEndpointContext;
import io.digitaljourney.platform.plugins.apps.appkar.AppProperties;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;

// @formatter:off
/**
 * Kar App Description App implementation of an {@link AbstractRSEndpointContext RS Context}.
 * 
 */
@Component(
	service = { Object.class, AppKarContext.class },
	immediate = true,
	scope = ServiceScope.SINGLETON,
	reference = {
		@Reference(
			name = AppProperties.REF_PLATFORM_SECURITY_MANAGER,
			service = PlatformSecurityManager.class,
            		policy = ReferencePolicy.DYNAMIC,
            		cardinality = ReferenceCardinality.OPTIONAL),
        	@Reference(
            		name = AppProperties.REF_SYSTEM_SECURITY_MANAGER,
            		service = PlatformSecurityManager.class,			
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = AppProperties.REF_PLATFORM_INVOCATION_MANAGER,
			service = PlatformInvocationManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = AppProperties.REF_ASYNC_MANAGER,
			service = PlatformAsyncManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = AppProperties.REF_CACHE_MANAGER,
			service = PlatformCacheManager.class,
			cardinality = ReferenceCardinality.MANDATORY)
})
@DocumentationResource(AppProperties.DOCS_ADDRESS)
// @formatter:on
public class AppKarContext extends AbstractRSEndpointContext {
	
	private AppKarConfiguration config;

	
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx Component context
	 * @param cfg 
	 */
	@Activate
	public void activate(ComponentContext ctx, AppKarConfiguration cfg) {
		prepare(ctx);
		setConfig(cfg);
	}
	
	 /**
     * Method called on controller configuration changes.
     *
     * @param cfg Controller configuration
     */
	@Modified
	protected void modified(AppKarConfiguration cfg) {
		setConfig(cfg);
	}	
	
	/**
     * Gets the Application configuration.
     *
     * @return Application configuration (or null)
     */
	public AppKarConfiguration getConfig() {
		return config;
	}

	/**
     * Sets the Application configuration.
     *
     * @param config Application configuration to set
     */
	public void setConfig(AppKarConfiguration config) {
		this.config = config;
	}
	
}
