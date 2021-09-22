/*-
 * #%L
 * Apps :: App to manage categories :: App
 * %%
 * Copyright (C) 2016 - 2021 Digital Journey
 * %%
 * All rights reserved. This software is protected under several
 * Laws in various countries. All content, layout, design of this document are the
 * intellectual property of Digital Journey, Novabase Business Solutions S.A.
 * and its licensors. The disclosure,copying, adaptation, citation, transcription,
 * translation, modification, decompilation, reverse engineering, derivatives,
 * integration, development and/or any other form of total or partial use of the
 * content of this document and/or accessible through or via the contents, by any
 * possible means without the respective authorization or licensing by the owner of
 * the intellectual property rights is prohibited, the offenders being subject to civil
 * and/or criminal prosecution and liability. The user or licensee of all or part of this
 * document by any means may only use the document under the terms and conditions agreed
 * upon with the owner of the intellectual property rights, and for the purposes
 * justifying the granting of the license or authorization, without which the
 * unauthorized use may subject the offenders to civil or criminal prosecution
 * under applicable Laws.
 * #L%
 */
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.mvc.controller;

import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;

import io.digitaljourney.platform.modules.mvc.api.controller.AbstractController;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.mvc.CategoryManagementAppContext;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.mvc.CategoryManagementAppConfiguration;

/**
 * Abstract application controller which extends an {@link AbstractController
 * Abstract Controller}.
 */
public abstract class AbstractAppController extends AbstractController<CategoryManagementAppContext> {
  
	@ServiceReference
	private CategoryManagementAppContext ctx;
  	
	/**
	 * Method called whenever it is necessary to prepare the controller with new
	 * configurations.
	 *
	 * @param config App configuration
	 */
	protected void prepare(CategoryManagementAppConfiguration config) {
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
	protected CategoryManagementAppContext getCtx() {
		return this.ctx;
	}

	/**
	 * Gets the current application configuration.
	 *
	 * @return Application configuration or null
	 */
	protected CategoryManagementAppConfiguration getConfig() {
		return ctx != null ? ctx.getConfig() : null;
	}

}
