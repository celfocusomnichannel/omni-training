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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.mvc;

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
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.CategoryManagementAppProperties;
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.mvc.exception.CategoryManagementAppException;

// @formatter:off
/**
 * App to manage categories App implementation of an {@link AbstractMVCContext MVC Context}.
 * 
 */
@Component(
	service = { Object.class, CategoryManagementAppContext.class },
	reference = {
		@Reference(
			name = CategoryManagementAppProperties.REF_PLATFORM_SECURITY_MANAGER,
			service = PlatformSecurityManager.class,
            		policy = ReferencePolicy.DYNAMIC,
            		cardinality = ReferenceCardinality.OPTIONAL),
		@Reference(
	    	name = CategoryManagementAppProperties.REF_SYSTEM_SECURITY_MANAGER,
	    	service = SystemSecurityManager.class,			
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = CategoryManagementAppProperties.REF_PLATFORM_INVOCATION_MANAGER,
			service = PlatformInvocationManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = CategoryManagementAppProperties.REF_ASYNC_MANAGER,
			service = PlatformAsyncManager.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = CategoryManagementAppProperties.REF_CACHE_MANAGER,
			service = PlatformCacheManager.class,
			cardinality = ReferenceCardinality.MANDATORY)
})
@DocumentationResource(CategoryManagementAppProperties.DOCS_ADDRESS)
@Designate(ocd = CategoryManagementAppConfiguration.class)
// @formatter:on
public class CategoryManagementAppContext extends AbstractMVCContext {
	
	private CategoryManagementAppConfiguration config;

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx Component context
	 * @param cfg 
	 */
	@Activate
	public void activate(ComponentContext ctx, CategoryManagementAppConfiguration cfg) {
		prepare(ctx);
		setConfig(cfg);
	}
	
	@Modified
	protected void modified(CategoryManagementAppConfiguration cfg) {
		setConfig(cfg);
	}	
	
	public CategoryManagementAppConfiguration getConfig() {
		return config;
	}

	public void setConfig(CategoryManagementAppConfiguration config) {
		this.config = config;
	}
	
	/**
	 * Creates a new App to manage categories Exception (500 - Internal Server Error) with the
	 * given error message.
	 *
	 * @param message Error message
	 * @return Created exception
	 */
	public CategoryManagementAppException exception(String message) {
		return CategoryManagementAppException.of(this, message);
	}

	/**
	 * Creates a new App to manage categories Exception (500 - Internal Server Error) with the
	 * given error cause.
	 *
	 * @param cause Error cause
	 * @return Created exception
	 */
	public CategoryManagementAppException exception(Throwable cause) {
		return CategoryManagementAppException.of(this, cause);
	}
}
