package io.digitaljourney.platform.plugins.apps.appkar.mvc;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.async.api.PlatformAsyncManager;
import io.digitaljourney.platform.modules.cache.api.PlatformCacheManager;
import io.digitaljourney.platform.modules.commons.annotation.DocumentationResource;
import io.digitaljourney.platform.modules.invocation.api.PlatformInvocationManager;
import io.digitaljourney.platform.modules.mvc.api.context.AbstractMVCContext;
import io.digitaljourney.platform.modules.security.api.PlatformSecurityManager;
import io.digitaljourney.platform.modules.security.api.SystemSecurityManager;
import io.digitaljourney.platform.plugins.apps.appkar.AppProperties;
import io.digitaljourney.platform.plugins.apps.appkar.mvc.exception.AppKarException;

// @formatter:off
/**
 * Kar App Description App implementation of an {@link AbstractMVCContext MVC Context}.
 * 
 */
@Component(
	service = { Object.class, AppKarContext.class },
	reference = {
		@Reference(
			name = AppProperties.REF_PLATFORM_SECURITY_MANAGER,
			service = PlatformSecurityManager.class,
            		policy = ReferencePolicy.DYNAMIC,
            		cardinality = ReferenceCardinality.OPTIONAL),
		@Reference(
	    	name = AppProperties.REF_SYSTEM_SECURITY_MANAGER,
	    	service = SystemSecurityManager.class,			
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
@Designate(ocd = AppKarConfiguration.class)
// @formatter:on
public class AppKarContext extends AbstractMVCContext {
	
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
	
	@Modified
	protected void modified(AppKarConfiguration cfg) {
		setConfig(cfg);
	}	
	
	public AppKarConfiguration getConfig() {
		return config;
	}

	public void setConfig(AppKarConfiguration config) {
		this.config = config;
	}
	
	/**
	 * Creates a new Kar App Description Exception (500 - Internal Server Error) with the
	 * given error message.
	 *
	 * @param message Error message
	 * @return Created exception
	 */
	public AppKarException exception(String message) {
		return AppKarException.of(this, message);
	}

	/**
	 * Creates a new Kar App Description Exception (500 - Internal Server Error) with the
	 * given error cause.
	 *
	 * @param cause Error cause
	 * @return Created exception
	 */
	public AppKarException exception(Throwable cause) {
		return AppKarException.of(this, cause);
	}
}
