package io.digitaljourney.platform.plugins.apps.ordermanagement.mvc;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.async.api.PlatformAsyncManager;
import io.digitaljourney.platform.modules.cache.api.PlatformCacheManager;
import io.digitaljourney.platform.modules.commons.annotation.DocumentationResource;
import io.digitaljourney.platform.modules.invocation.api.PlatformInvocationManager;
import io.digitaljourney.platform.modules.security.api.PlatformSecurityManager;
import io.digitaljourney.platform.modules.security.api.SystemSecurityManager;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.exception.OrderManagementException;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.api.JourneyWorkflowEngineResource;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.context.AbstractJourneyContext;

// @formatter:off
/**
 * Sample App Description implementation of an {@link AbstractJourneyContext}.
 *
 */
@Component(
	service = { AppContext.class },
			configurationPid = AppConfiguration.CPID,
			configurationPolicy = ConfigurationPolicy.OPTIONAL,
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
				cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
				name = AppProperties.REF_JOURNEY_ENGINE,
				service = JourneyWorkflowEngineResource.class,
				cardinality = ReferenceCardinality.MANDATORY)
})
@DocumentationResource(AppProperties.DOCS_ADDRESS)
@Designate(ocd = AppConfiguration.class)
// @formatter:on
public class AppContext extends AbstractJourneyContext {

	private AppConfiguration config;

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx Component context
	 * @param cfg
	 */
	@Activate
	public void activate(ComponentContext ctx, AppConfiguration cfg) {
		prepare(ctx);
		setConfig(cfg);
	}

	@Modified
	protected void modified(AppConfiguration cfg) {
		setConfig(cfg);
	}

	public AppConfiguration getConfig() {
		return config;
	}

	public void setConfig(AppConfiguration config) {
		this.config = config;
	}

	/**
	 * Creates a new Sample Kar App Description Exception (500 - Internal Server Error) with the
	 * given error message.
	 *
	 * @param message Error message
	 * @return Created exception
	 */
	public OrderManagementException exception(String message) {
		return OrderManagementException.of(this, message);
	}

	/**
	 * Creates a new Sample Kar App Description Exception (500 - Internal Server Error) with the
	 * given error cause.
	 *
	 * @param cause Error cause
	 * @return Created exception
	 */
	public OrderManagementException exception(Throwable cause) {
		return OrderManagementException.of(this, cause);
	}
}
