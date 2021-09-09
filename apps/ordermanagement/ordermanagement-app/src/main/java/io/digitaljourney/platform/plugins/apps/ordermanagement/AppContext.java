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
package io.digitaljourney.platform.plugins.apps.ordermanagement;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import io.digitaljourney.platform.modules.async.api.PlatformAsyncManager;
import io.digitaljourney.platform.modules.cache.api.PlatformCacheManager;
import io.digitaljourney.platform.modules.commons.annotation.DocumentationResource;
import io.digitaljourney.platform.modules.invocation.api.PlatformInvocationManager;
import io.digitaljourney.platform.modules.security.api.PlatformSecurityManager;
import io.digitaljourney.platform.modules.security.api.SystemSecurityManager;
import io.digitaljourney.platform.plugins.apps.ordermanagement.exception.OrderManagementException;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.api.JourneyWorkflowEngineResource;
import io.digitaljourney.platform.plugins.modules.journeyworkflowengine.gateway.aspect.context.AbstractJourneyContext;

// @formatter:off
@Component(
	service = { Object.class, AppContext.class },
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
// @formatter:on
public class AppContext extends AbstractJourneyContext {
	@Activate
	public void activate(ComponentContext ctx) {
		prepare(ctx);
	}

	public OrderManagementException exception(String message) {
		return OrderManagementException.of(this, message);
	}

	public OrderManagementException exception(Throwable cause) {
		return OrderManagementException.of(this, cause);
	}
}
