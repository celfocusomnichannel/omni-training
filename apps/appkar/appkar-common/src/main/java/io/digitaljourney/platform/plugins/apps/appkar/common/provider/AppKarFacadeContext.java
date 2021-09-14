package io.digitaljourney.platform.plugins.apps.appkar.common.provider;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ServiceScope;

// @formatter:off

import io.digitaljourney.platform.modules.async.api.PlatformAsyncManager;
import io.digitaljourney.platform.modules.cache.api.PlatformCacheManager;
import io.digitaljourney.platform.modules.invocation.api.PlatformInvocationManager;
import io.digitaljourney.platform.modules.security.api.PlatformSecurityManager;
import io.digitaljourney.platform.modules.security.api.context.AbstractSecurityContext;
import io.digitaljourney.platform.plugins.apps.appkar.AppProperties;

// @formatter:off
@Component(
	service = { AppKarFacadeContext.class },
	immediate = true,
	scope = ServiceScope.SINGLETON,
	reference = {
			@Reference(
				name = AppProperties.REF_PLATFORM_SECURITY_MANAGER,
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
	}
)
// @formatter:on
public class AppKarFacadeContext extends AbstractSecurityContext {

	private AppKarFacadeConfig config;

	@Activate
	public void activate(ComponentContext ctx, AppKarFacadeConfig cfg) {
		prepare(ctx);
		setConfig(cfg);
	}

	@Modified
	protected void modified(AppKarFacadeConfig cfg) {
		setConfig(cfg);
	}

	public AppKarFacadeConfig getConfig() {
		return config;
	}

	public void setConfig(AppKarFacadeConfig config) {
		this.config = config;
	}
}
