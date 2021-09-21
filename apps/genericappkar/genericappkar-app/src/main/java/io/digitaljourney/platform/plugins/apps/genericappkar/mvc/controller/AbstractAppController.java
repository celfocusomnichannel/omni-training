package io.digitaljourney.platform.plugins.apps.genericappkar.mvc.controller;

import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;

import io.digitaljourney.platform.modules.mvc.api.controller.AbstractController;
import io.digitaljourney.platform.plugins.apps.genericappkar.mvc.GenericAppKarContext;
import io.digitaljourney.platform.plugins.apps.genericappkar.mvc.GenericAppKarConfiguration;

/**
 * Abstract application controller which extends an {@link AbstractController
 * Abstract Controller}.
 */
public abstract class AbstractAppController extends AbstractController<GenericAppKarContext> {
  
	@ServiceReference
	private GenericAppKarContext ctx;
  	
	/**
	 * Method called whenever it is necessary to prepare the controller with new
	 * configurations.
	 *
	 * @param config App configuration
	 */
	protected void prepare(GenericAppKarConfiguration config) {
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
	protected GenericAppKarContext getCtx() {
		return this.ctx;
	}

	/**
	 * Gets the current application configuration.
	 *
	 * @return Application configuration or null
	 */
	protected GenericAppKarConfiguration getConfig() {
		return ctx != null ? ctx.getConfig() : null;
	}

}
