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
package io.digitaljourney.platform.plugins.apps.ordermanagement.agent.repository;

import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.serviceusermapping.ServiceUserMapped;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.jcr.sling.api.dao.BaseSlingDAO;
import io.digitaljourney.platform.modules.jcr.sling.api.resourceresolver.SlingResorceResolverProvider;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppContext;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;

//@formatter:off
@Component(
	service = { Object.class, RepositoryAgentImpl.class },
	configurationPid = RepositoryAgentConfig.CPID,
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	reference = {
		@Reference(
			name = AppProperties.REF_CONTEXT,
			service = AppContext.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = AppProperties.REF_RESOURCE_FACTORY,
			service = ResourceResolverFactory.class,
			cardinality = ReferenceCardinality.MANDATORY),
		@Reference(
			name = AppProperties.REF_RESOURCE_RESOLVER_PROVIDER,
			service = SlingResorceResolverProvider.class,
			cardinality = ReferenceCardinality.MANDATORY)
		})
@Designate(ocd = RepositoryAgentConfig.class)
//@formatter:on
public class RepositoryAgentImpl extends BaseSlingDAO<RepositoryAgentConfig> {
	@Reference
	private ServiceUserMapped serviceUserMapped;

    @Reference
    private AppContext ctx;
    
	@Activate
	public void activate(RepositoryAgentConfig config) {
		prepare(config);
	}
}
