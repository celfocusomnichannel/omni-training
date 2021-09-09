/*-
 * #%L
 * Apps :: Training JWE Order Management App :: CXF
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
package io.digitaljourney.platform.plugins.apps.ordermanagement.cxf.resource;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.ws.rs.api.resource.AbstractResource;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.api.OrderManagementResource;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.facade.OrderManagementFacade;
import io.digitaljourney.platform.plugins.apps.ordermanagement.cxf.OrderManagementConfiguration;
import io.digitaljourney.platform.plugins.apps.ordermanagement.cxf.OrderManagementContext;
import io.digitaljourney.platform.plugins.providers.rsprovider.annotations.CustomRsProvider;

//@formatter:off
//OSGi setup and configuration.
@Component(
	service = { OrderManagementResource.class },
	configurationPid = OrderManagementConfiguration.CPID,
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	reference = {
		@Reference (
			name = AppProperties.REF_CONTEXT,
			service = OrderManagementContext.class,
			cardinality = ReferenceCardinality.MANDATORY
		)
	},
	property = {
		"digitaljourney.service.name=OrderManagement"
	}
)
//@formatter:on
@Designate(ocd = OrderManagementConfiguration.class)
@CustomRsProvider(AppProperties.ADDRESS)
public class OrderManagementResourceImpl extends AbstractResource<OrderManagementContext, OrderManagementConfiguration>
	implements OrderManagementResource {

	@Reference
	private volatile OrderManagementFacade facade;

	/**
	 * Method called whenever the component is activated.
	 *
	 * @param ctx    Component context
	 * @param config Component configuration
	 */
	@Activate
	public void activate(ComponentContext ctx, OrderManagementConfiguration config) {
		prepare(ctx, config);
	}

	/**
	 * Method called whenever the component configuration is modified.
	 *
	 * @param config Component configuration
	 */
	@Modified
	public void modified(OrderManagementConfiguration config) {
		prepare(config);
	}
	
	@Override
	public String echo(String msg) {
		return facade.echo(msg);
	}

	@Override
	public String secureEcho(String channel, String msg) {
		return facade.secureEcho(channel, msg);
	}

}
