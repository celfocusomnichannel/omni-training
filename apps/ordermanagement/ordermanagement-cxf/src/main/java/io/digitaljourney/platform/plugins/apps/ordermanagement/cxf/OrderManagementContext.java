/*-
 * #%L
 * Apps :: Training JWE Order Management App App
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
package io.digitaljourney.platform.plugins.apps.ordermanagement.cxf;

import io.digitaljourney.platform.modules.async.api.PlatformAsyncManager;
import io.digitaljourney.platform.modules.cache.api.PlatformCacheManager;
import io.digitaljourney.platform.modules.commons.annotation.DocumentationResource;
import io.digitaljourney.platform.modules.invocation.api.PlatformInvocationManager;
import io.digitaljourney.platform.modules.security.api.PlatformSecurityManager;
import io.digitaljourney.platform.modules.ws.rs.api.context.AbstractRSEndpointContext;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;

// @formatter:off
/**
 * Training JWE Order Management App App implementation of an {@link AbstractRSEndpointContext RS Context}.
 * 
 */
@Component(
	service = { Object.class, OrderManagementContext.class },
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
public class OrderManagementContext extends AbstractRSEndpointContext {
	
	private OrderManagementConfiguration config;

	
	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx Component context
	 * @param cfg 
	 */
	@Activate
	public void activate(ComponentContext ctx, OrderManagementConfiguration cfg) {
		prepare(ctx);
		setConfig(cfg);
	}
	
	 /**
     * Method called on controller configuration changes.
     *
     * @param cfg Controller configuration
     */
	@Modified
	protected void modified(OrderManagementConfiguration cfg) {
		setConfig(cfg);
	}	
	
	/**
     * Gets the Application configuration.
     *
     * @return Application configuration (or null)
     */
	public OrderManagementConfiguration getConfig() {
		return config;
	}

	/**
     * Sets the Application configuration.
     *
     * @param config Application configuration to set
     */
	public void setConfig(OrderManagementConfiguration config) {
		this.config = config;
	}
	
}
