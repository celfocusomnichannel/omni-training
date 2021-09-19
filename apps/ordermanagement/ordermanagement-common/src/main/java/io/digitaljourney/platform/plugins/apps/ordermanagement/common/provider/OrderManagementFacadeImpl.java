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
package io.digitaljourney.platform.plugins.apps.ordermanagement.common.provider;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;

import io.digitaljourney.platform.modules.security.api.AbstractSecurityComponent;
import io.digitaljourney.platform.plugins.apps.ordermanagement.AppProperties;
import io.digitaljourney.platform.plugins.apps.ordermanagement.agent.OrderManagementCoreAgent;
import io.digitaljourney.platform.plugins.apps.ordermanagement.common.api.facade.OrderManagementFacade;

//@formatter:off
@Component(
        service = { OrderManagementFacade.class },
        configurationPid = OrderManagementFacadeConfig.CPID,
        configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = OrderManagementFacadeConfig.class)
//@formatter:on
public class OrderManagementFacadeImpl extends AbstractSecurityComponent<OrderManagementFacadeContext, OrderManagementFacadeConfig> implements OrderManagementFacade {

	/** Core Agent instance */
    @Reference(cardinality = ReferenceCardinality.OPTIONAL, policyOption = ReferencePolicyOption.GREEDY, policy = ReferencePolicy.DYNAMIC)
    private volatile OrderManagementCoreAgent coreAgent;
    
    
    @Override
    public String echo(String msg) {
    	return getCoreAgent().echo(msg);
    }
    
    @RequiresPermissions(AppProperties.PERMISSION_READ)
    @Override
    public String secureEcho(String channel, String msg) {
    	return getCoreAgent().secureEcho(channel, msg);
    }

    private OrderManagementCoreAgent getCoreAgent() {
        if (coreAgent == null) {
            coreAgent = getService(OrderManagementCoreAgent.class);
        }
        return coreAgent;
    }
}