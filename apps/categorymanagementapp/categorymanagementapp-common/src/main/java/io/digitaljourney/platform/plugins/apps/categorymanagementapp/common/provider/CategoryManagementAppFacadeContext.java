/*-
 * #%L
 * Apps :: App to manage categories :: Common Facade
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
package io.digitaljourney.platform.plugins.apps.categorymanagementapp.common.provider;

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
import io.digitaljourney.platform.plugins.apps.categorymanagementapp.CategoryManagementAppProperties;

// @formatter:off
@Component(
	service = { CategoryManagementAppFacadeContext.class },
	immediate = true,
	scope = ServiceScope.SINGLETON,
	reference = {
			@Reference(
				name = CategoryManagementAppProperties.REF_PLATFORM_SECURITY_MANAGER,
				service = PlatformSecurityManager.class,
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
	}
)
// @formatter:on
public class CategoryManagementAppFacadeContext extends AbstractSecurityContext {

	private CategoryManagementAppFacadeConfig config;

	@Activate
	public void activate(ComponentContext ctx, CategoryManagementAppFacadeConfig cfg) {
		prepare(ctx);
		setConfig(cfg);
	}

	@Modified
	protected void modified(CategoryManagementAppFacadeConfig cfg) {
		setConfig(cfg);
	}

	public CategoryManagementAppFacadeConfig getConfig() {
		return config;
	}

	public void setConfig(CategoryManagementAppFacadeConfig config) {
		this.config = config;
	}
}
