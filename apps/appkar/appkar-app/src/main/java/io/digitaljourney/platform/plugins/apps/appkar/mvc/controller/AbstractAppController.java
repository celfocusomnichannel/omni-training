package io.digitaljourney.platform.plugins.apps.appkar.mvc.controller;

import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;

import io.digitaljourney.platform.modules.mvc.api.controller.AbstractController;
import io.digitaljourney.platform.plugins.apps.appkar.mvc.AppKarContext;
import io.digitaljourney.platform.plugins.apps.appkar.mvc.AppKarConfiguration;

/**
 * Abstract application controller which extends an {@link AbstractController
 * Abstract Controller}.
 */
public abstract class AbstractAppController extends AbstractController<AppKarContext> {
  
	@ServiceReference
	private AppKarContext ctx;
  	
	/**
	 * Method called whenever it is necessary to prepare the controller with new
	 * configurations.
	 *
	 * @param config App configuration
	 */
	protected void prepare(AppKarConfiguration config) {
		if (ctx != null) {
			ctx.setConfig(config);
		}
	}
	
	/**
	 * Gets the application context.
	 *
	 * @return Context
	 */
	@Override
	protected AppKarContext getCtx() {
		return this.ctx;
	}

	/**
	 * Gets the current application configuration.
	 *
	 * @return Application configuration or null
	 */
	protected AppKarConfiguration getConfig() {
		return ctx != null ? ctx.getConfig() : null;
	}

}
